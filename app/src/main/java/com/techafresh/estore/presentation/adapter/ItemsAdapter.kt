package com.techafresh.estore.presentation.adapter

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.techafresh.estore.MainActivity
import com.techafresh.estore.R
import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.data.dataclasses.Rating
import com.techafresh.estore.data.db.CartViewModel
import com.techafresh.estore.data.db.model.CartItem
import com.techafresh.estore.databinding.CardItemBinding

class ItemsAdapter(var products : List<ProductsItem> , private val viewModel: CartViewModel) :  RecyclerView.Adapter<ItemsAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(val binding : CardItemBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(product : ProductsItem){
            binding.textViewPrice.text = "$" + product.price.toString()
            binding.textViewTitle.text = product.title
            Glide.with(binding.imageViewItem.context).load(product.image).into(binding.imageViewItem)

            var notPressed = 0

            binding.buttonAddToCart.setOnClickListener {
                if (notPressed == 0){
                val cartItem : CartItem = CartItem(
                category = product.category,
                description = product.description,
                product_id = product.id,
                image = product.image,
                price = product.price,
                rating = Rating(0,0.0),
                title = product.title,
                quantity = 1
                )
                viewModel.insert(cartItem)
                Toast.makeText(binding.imageViewItem.context, "Added To Cart Successfully", Toast.LENGTH_SHORT).show()
                notPressed = 1
                }
                // Will turn into a snack bar


//                val sharedPref = binding.root.context.getSharedPreferences("cart", 0)
//                val editor: SharedPreferences.Editor = sharedPref.edit()
//                val gson = Gson()
//                val selectedProductJson : String = gson.toJson(product)
//                editor.putString("cart_item ${product.id}", selectedProductJson)
//                editor.apply()
            }

            binding.root.setOnClickListener {
//                Toast.makeText(binding.imageViewItem.context, "Loading...", Toast.LENGTH_SHORT).show()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
        setScaleAnimation(holder.binding.root)
    }
}