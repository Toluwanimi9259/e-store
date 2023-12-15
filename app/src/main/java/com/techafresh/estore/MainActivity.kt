package com.techafresh.estore

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.techafresh.estore.databinding.ActivityMainBinding
import com.techafresh.estore.presentation.viewmodel.StoreViewModel
import com.techafresh.estore.presentation.viewmodel.factory.StoreViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var storeViewModelFactory: StoreViewModelFactory

    lateinit var binding : ActivityMainBinding
    lateinit var storeViewModel: StoreViewModel

    lateinit var sharedPreferences : SharedPreferences

    lateinit var navHostFragment: NavHostFragment

    lateinit var navController: NavController

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewXX) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        storeViewModel = ViewModelProvider(this , storeViewModelFactory)[StoreViewModel::class.java]

        sharedPreferences = getSharedPreferences("cart", MODE_PRIVATE)


//        CoroutineScope(Main).launch {
            storeViewModel.getAllProducts().observe(this@MainActivity , Observer {
                Log.d("MYTAG" , it[1].toString())
                Toast.makeText(this@MainActivity, "I think we got something", Toast.LENGTH_LONG).show()
            })
//        }

    }
}