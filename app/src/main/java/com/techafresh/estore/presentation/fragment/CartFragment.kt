package com.techafresh.estore.presentation.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.techafresh.estore.MainActivity
import com.techafresh.estore.R
import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.databinding.FragmentCartBinding
import com.techafresh.estore.databinding.FragmentDetailBinding
import com.techafresh.estore.presentation.adapter.CartAdapter
import com.techafresh.estore.presentation.adapter.ItemsAdapter


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var cartAdapter: CartAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        sharedPreferences = (activity as MainActivity).sharedPreferences

        initRV(getAllValuesFromSharePref())

    }

    private fun getAllValuesFromSharePref() : List<ProductsItem>{
        val json_list = sharedPreferences.all.values.toList()
        val gson = Gson()
        val newList = arrayListOf<ProductsItem>()
        for (i in json_list.indices){
            newList.add(gson.fromJson(json_list[i].toString() , ProductsItem::class.java))
        }
        return newList
    }

    private fun initRV(products : List <ProductsItem>){
        cartAdapter = CartAdapter(products)
        binding.cartRV.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}