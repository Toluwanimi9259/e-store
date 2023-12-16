package com.techafresh.estore.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techafresh.estore.data.db.CartViewModel
import com.techafresh.estore.data.db.HistoryItem
import com.techafresh.estore.databinding.CartItemBinding
import com.techafresh.estore.databinding.HistoryItemBinding

class HistoryAdapter(
    var history : List<HistoryItem>,
    private val viewModel: CartViewModel
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){

    inner class HistoryViewHolder(val binding: HistoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = history.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyITem = history[position]
        setScaleAnimation(holder.binding.root)

        holder.binding.textViewQty.text = "Qty = ${historyITem.quantity}"
        holder.binding.textViewCartPriceHistory.text = "Price = $${historyITem.price}"
        holder.binding.textViewCartTitleHistory.text = historyITem.title
        holder.binding.textViewPriceTotalHistory.text = "$${historyITem.price * historyITem.quantity}"
        Glide.with(holder.binding.imageViewCardItemHistory.context).load(historyITem.image).into(holder.binding.imageViewCardItemHistory)

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
}