package com.techafresh.estore.data.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techafresh.estore.data.db.model.CartItem
import com.techafresh.estore.databinding.CartItemBinding


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

        setScaleAnimation(holder.binding.root)

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

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }

    private fun setScaleAnimation(view: View) {
        val anim = ScaleAnimation(
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        anim.duration = 500
        view.startAnimation(anim)
    }

    inner class CartItemViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)
}
