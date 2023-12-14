package com.techafresh.estore

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techafresh.estore.presentation.viewmodel.StoreViewModel
import com.techafresh.estore.presentation.viewmodel.factory.StoreViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var storeViewModelFactory: StoreViewModelFactory

//    lateinit var binding : ActivityMain

    lateinit var storeViewModel: StoreViewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storeViewModel = ViewModelProvider(this , storeViewModelFactory)[StoreViewModel::class.java]

//        CoroutineScope(Main).launch {
            storeViewModel.getAllProducts().observe(this@MainActivity , Observer {
                Log.d("MYTAG" , it[1].toString())
                Toast.makeText(this@MainActivity, "I think we got something", Toast.LENGTH_LONG).show()
            })
//        }

    }
}