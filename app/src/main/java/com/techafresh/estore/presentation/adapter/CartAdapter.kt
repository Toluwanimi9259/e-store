package com.techafresh.estore.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techafresh.estore.R
import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.databinding.CardItemBinding
import com.techafresh.estore.databinding.CartItemBinding

class CartAdapter(var productsInCart : List<ProductsItem>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(product : ProductsItem){
            binding.textViewCartPrice.text = "$" + product.price.toString()
            binding.textViewCartTitle.text = product.title
            Glide.with(binding.imageViewCardItem.context).load(product.image).into(binding.imageViewCardItem)

            binding.root.setOnClickListener {
                Toast.makeText(binding.imageViewCardItem.context, "Loading...", Toast.LENGTH_SHORT).show()
                val selectedProduct = product

                val bundle = bundleOf(
                    "price" to selectedProduct.price,
                    "title" to selectedProduct.title,
                    "imgURL" to selectedProduct.image,
                    "id" to selectedProduct.id,
                    "category" to selectedProduct.category,
                    "description" to selectedProduct.description
                )

                it.findNavController().navigate(R.id.action_cartFragment2_to_detailFragment , bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = productsInCart.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(productsInCart[position])
    }
}