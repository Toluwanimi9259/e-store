package com.techafresh.estore.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.databinding.CardItemBinding

class ItemsAdapter(var products : List<ProductsItem>) :  RecyclerView.Adapter<ItemsAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(private val binding : CardItemBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(product : ProductsItem){
            binding.textViewPrice.text = "$ " + product.price.toString()
            binding.textViewTitle.text = product.title
            Glide.with(binding.imageViewItem.context).load(product.image).into(binding.imageViewItem)
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