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

class ItemsAdapter(var products : List<ProductsItem>) :  RecyclerView.Adapter<ItemsAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(private val binding : CardItemBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(product : ProductsItem){
            binding.textViewPrice.text = "$" + product.price.toString()
            binding.textViewTitle.text = product.title
            Glide.with(binding.imageViewItem.context).load(product.image).into(binding.imageViewItem)

            binding.root.setOnClickListener {
                Toast.makeText(binding.imageViewItem.context, "Loading...", Toast.LENGTH_SHORT).show()
                val selectedProduct = product

                val bundle = bundleOf(
                    "price" to selectedProduct.price,
                    "title" to selectedProduct.title,
                    "imgURL" to selectedProduct.image,
                    "id" to selectedProduct.id,
                    "category" to selectedProduct.category,
                    "description" to selectedProduct.description
                )

                it.findNavController().navigate(R.id.action_homeFragment2_to_detailFragment , bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }
}