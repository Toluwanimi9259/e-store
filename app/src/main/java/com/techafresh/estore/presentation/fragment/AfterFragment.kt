package com.techafresh.estore.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.techafresh.estore.R
import com.techafresh.estore.databinding.FragmentAfterBinding
import com.techafresh.estore.databinding.FragmentCartBinding
import com.techafresh.estore.databinding.FragmentDetailBinding

class AfterFragment : Fragment() {

    private lateinit var binding: FragmentAfterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_after, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAfterBinding.bind(view)

        binding.imageViewBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_afterFragment_to_cartFragment)
        }

        binding.buttonViewHistory.setOnClickListener {
            it.findNavController().navigate(R.id.action_afterFragment_to_historyFragment)
        }

        binding.buttonExploreMore.setOnClickListener {
            it.findNavController().navigate(R.id.action_afterFragment_to_homeFragment)
        }
    }
}