package com.techafresh.estore.presentation.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.techafresh.estore.MainActivity
import com.techafresh.estore.R
import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.data.db.CartItemAdapter
import com.techafresh.estore.data.db.CartViewModel
import com.techafresh.estore.data.db.HistoryItem
import com.techafresh.estore.data.db.model.CartItem
import com.techafresh.estore.databinding.FragmentCartBinding
import com.techafresh.estore.databinding.FragmentDetailBinding
import com.techafresh.estore.presentation.adapter.CartAdapter
import com.techafresh.estore.presentation.adapter.ItemsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var cartAdapter: CartAdapter

    private lateinit var cartViewModel: CartViewModel

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
        cartViewModel = (activity as MainActivity).viewModel

        val adapter = CartItemAdapter(listOf(), cartViewModel)

        binding.imageViewBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_cartFragment_to_homeFragment)
        }

        binding.button.setOnClickListener {
            if(binding.placeholder1.text == "Empty Shopping Cart"){
                Snackbar.make(requireView(), "Your Cart is Empty" , Snackbar.LENGTH_LONG)
                    .apply { show()
                    }
            }else{
                addToHistory()
                CoroutineScope(IO).launch {
                    cartViewModel.clearCart()
                }
                it.findNavController().navigate(R.id.action_cartFragment_to_afterFragment)
            }
        }

        cartViewModel.getAllShoppingItems().observe(viewLifecycleOwner, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
            if (it.isEmpty()) {
                binding.placeholder1.text = "Empty Shopping Cart"
            }else{
                initRv1(adapter)
            }
            // Calculate Subtotal
            var total = it.map { it.quantity * it.price }.sum()
            val roundedNum = "%.2f".format(total)
            binding.textViewTotal.text = "Total: $$roundedNum"
            Log.d("MYTAG", "Total list of items: ${it}")

        })
    }

    private fun addToHistory(){
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
        cartViewModel.getAllShoppingItems().observe(viewLifecycleOwner , Observer{
            for (i in it.indices){
                cartViewModel.insertHistory(
                    HistoryItem(
                        image = it[i].image,
                        category = it[i].category,
                        description = it[i].description,
                        product_id = it[i].product_id,
                        price = it[i].price,
                        rating = it[i].rating,
                        quantity = it[i].quantity,
                        title = it[i].title,
                        orderDate = simpleDateFormat.format(Calendar.getInstance().time)
                    )
                )
            }
        })

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

    private fun initRv1(cartAdapter: CartItemAdapter){
        binding.cartRV.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}