package com.techafresh.estore.presentation.fragment

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.techafresh.estore.MainActivity
import com.techafresh.estore.R
import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.data.dataclasses.Rating
import com.techafresh.estore.data.db.CartViewModel
import com.techafresh.estore.data.db.model.CartItem
import com.techafresh.estore.databinding.FragmentDetailBinding
import com.techafresh.estore.presentation.viewmodel.StoreViewModel


class DetailFragment : Fragment() {

    private lateinit var storeViewModel: StoreViewModel

    private lateinit var binding: FragmentDetailBinding

    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        storeViewModel = (activity as MainActivity).storeViewModel
        sharedPreferences = (activity as MainActivity).sharedPreferences
        cartViewModel = (activity as MainActivity).viewModel

        val selected_product = ProductsItem(
            category = requireArguments().getString("category")!!,
            description = requireArguments().getString("description")!!,
            id = requireArguments().getInt("id"),
            image = requireArguments().getString("imgURL")!!,
            price = requireArguments().getDouble("price"),
            rating = Rating(0,0.0),
            title = requireArguments().getString("title")!!
        )

        binding.imageViewBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_detailFragment_to_homeFragment2)
        }

        binding.buttonAddToCartDetail.setOnClickListener {
            val cartItem : CartItem = CartItem(
                category = selected_product.category,
                description = selected_product.description,
                product_id = selected_product.id,
                image = selected_product.image,
                price = selected_product.price,
                rating = Rating(0,0.0),
                title = selected_product.title,
                quantity = 1
            )
            cartViewModel.insert(cartItem)

            Toast.makeText(activity, "Added To Cart Successfully", Toast.LENGTH_SHORT).show()
//            saveDataToCart(selected_product)
//            it.findNavController().navigate(R.id.action_detailFragment_to_cartFragment2)
        }

        try {
            binding.apply {
                Glide.with(this@DetailFragment).load(selected_product.image).into(binding.imageViewProductDetail)
                textViewTitleDetail.text = selected_product.title
                textViewCategoryDetail.text = "Category - " + selected_product.category
                textViewDescriptionDetail.text = selected_product.description
                textViewPriceDetail.text = "$" + selected_product.price.toString()
            }
        }catch (ex : Exception){
            Log.d("MYTAG" , "There was a problem with the selected product")
        }
    }

    private fun saveDataToCart(selectedProduct : ProductsItem){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()
        val selectedProductJson : String = gson.toJson(selectedProduct)
        editor.putString("cart_item ${selectedProduct.id}", selectedProductJson)
        editor.apply()
    }

}