package com.example.githubapp.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubapp.R
import com.example.githubapp.ViewModel.MainViewModel
import com.example.githubapp.databinding.ProfileRepofragmentBinding
import com.example.githubapp.ui.Adapter.ProfileRepositoryAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileRepoFragment: Fragment(R.layout.profile_repofragment) {

    private lateinit var binding: ProfileRepofragmentBinding
    private val adapter = ProfileRepositoryAdapter()
    private val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ProfileRepofragmentBinding.bind(view)

        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenResumed {
            viewModel.getUserRepository()
        }
        binding.apply {
           icBackRepoProf.setOnClickListener {
               findNavController().popBackStack()
           }
        }
        initObservers()
    }

    private fun initObservers() {
        viewModel.getUserRepositoryFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            Toast.makeText(requireContext(), "Mag'luwmat kelmey qaldi", Toast.LENGTH_SHORT).show()
        }
    }
}