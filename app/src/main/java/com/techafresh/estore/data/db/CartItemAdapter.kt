package com.techafresh.estore.data.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techafresh.estore.R
import com.techafresh.estore.data.db.model.CartItem
import com.techafresh.estore.databinding.CartItemBinding
import com.techafresh.estore.databinding.CartTwoItemBinding

class CartItemAdapter (
    var items: List<CartItem>,
    private val viewModel: CartViewModel
) : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>()
{

    private val callback = object : DiffUtil.ItemCallback<CartItem>(){

        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.quantity == newItem.quantity
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this , callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val curShoppingItem = items[position]

        holder.binding.textViewCartTitle.text = curShoppingItem.title
        holder.binding.textView2.text = "${curShoppingItem.quantity}"
        holder.binding.textViewCartPrice.text = "$${curShoppingItem.price}"
        holder.binding.textViewPriceTotal.text = "$${curShoppingItem.price * curShoppingItem.quantity}"
        Glide.with(holder.binding.imageViewCardItem.context).load(curShoppingItem.image).into(holder.binding.imageViewCardItem)

//        holder.binding.ivDelete.setOnClickListener {
//            viewModel.delete(curShoppingItem)
//        }

        holder.binding.imageViewIncrease.setOnClickListener {
            curShoppingItem.quantity++
            viewModel.insert(curShoppingItem)
        }

        holder.binding.imageViewReduce.setOnClickListener {
            if (curShoppingItem.quantity > 1) {
                curShoppingItem.quantity--
                viewModel.insert(curShoppingItem)
            }
        }

        holder.binding.imageView.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

//        holder.binding.ivEdit.setOnClickListener {
//
//        }
    }

    inner class CartItemViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)
}
