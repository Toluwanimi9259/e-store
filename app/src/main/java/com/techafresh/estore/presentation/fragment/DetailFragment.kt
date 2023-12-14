package com.techafresh.estore.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.techafresh.estore.MainActivity
import com.techafresh.estore.R
import com.techafresh.estore.data.dataclasses.ProductsItem
import com.techafresh.estore.data.dataclasses.Rating
import com.techafresh.estore.databinding.FragmentDetailBinding
import com.techafresh.estore.presentation.viewmodel.StoreViewModel
import java.lang.Exception


class DetailFragment : Fragment() {

    private lateinit var storeViewModel: StoreViewModel

    private lateinit var binding: FragmentDetailBinding

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

}