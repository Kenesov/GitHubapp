package com.example.githubapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.githubapp.MainActivity
import com.example.githubapp.R
import com.example.githubapp.databinding.HomefragmentBinding

class HomeFragment: Fragment(R.layout.homefragment) {
    private lateinit var binding: HomefragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = HomefragmentBinding.bind(view)

        initListeners()
    }

    private fun initListeners() {
        binding.apply {

            fouthContainer.setOnClickListener {
              findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRepositoriesFragment())
            }
            search.setOnClickListener {
              findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visilityOfBottomNavigation(View.VISIBLE)
    }
}