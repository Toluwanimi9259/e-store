package com.techafresh.estore.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.techafresh.estore.MainActivity
import com.techafresh.estore.R
import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.databinding.FragmentHomeBinding
import com.techafresh.estore.presentation.adapter.ItemsAdapter
import com.techafresh.estore.presentation.viewmodel.StoreViewModel

class HomeFragment : Fragment() {

    private lateinit var storeViewModel: StoreViewModel

    private lateinit var binding: FragmentHomeBinding

    lateinit var itemsAdapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        storeViewModel = (activity as MainActivity).storeViewModel

        getAllProducts()
    }

    private fun initRV(products : List <ProductsItem>){
        itemsAdapter = ItemsAdapter(products)
        binding.recV.apply {
            adapter = itemsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun getAllProducts(){
        storeViewModel.getAllProducts().observe(viewLifecycleOwner, Observer {
            initRV(it)
        })
    }
}