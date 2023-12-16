package com.techafresh.estore.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.techafresh.estore.MainActivity
import com.techafresh.estore.R
import com.techafresh.estore.data.db.CartItemAdapter
import com.techafresh.estore.data.db.CartViewModel
import com.techafresh.estore.databinding.FragmentCartBinding
import com.techafresh.estore.databinding.FragmentHistoryBinding
import com.techafresh.estore.presentation.adapter.CartAdapter
import com.techafresh.estore.presentation.adapter.HistoryAdapter


class HistoryFragment : Fragment() {

    private lateinit var cartViewModel: CartViewModel

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding =  FragmentHistoryBinding.bind(view)
        cartViewModel = (activity as MainActivity).viewModel

        val historyAdapter = HistoryAdapter(listOf(), cartViewModel)

        cartViewModel.getFullHistory().observe(viewLifecycleOwner , Observer {
            historyAdapter.history = it
            historyAdapter.notifyDataSetChanged()
            initRV(historyAdapter)

            if (it.isEmpty()) {
                binding.placeholder.text = "You haven't Ordered anything yet"
            }
            // Calculate Subtotal
            var total = it.map { it.quantity * it.price }.sum()
            val roundedNum = "%.2f".format(total)
            binding.textView4.text = "Total Spent =  $$roundedNum"
            Log.d("MYTAG", "Total list of History: ${it}")
        })
    }

    private fun initRV(hadapter: HistoryAdapter){
        binding.hRV.apply {
            adapter = hadapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}