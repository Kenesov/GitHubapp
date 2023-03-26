package com.example.githubapp.ui.repository

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.githubapp.MainActivity
import com.example.githubapp.R
import com.example.githubapp.ViewModel.MainViewModel
import com.example.githubapp.databinding.RepositoryfragmentBinding
import com.example.githubapp.ui.Adapter.RepositoryAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReporisotryFragment: Fragment(R.layout.repositoryfragment) {

    private lateinit var binding: RepositoryfragmentBinding
    private val adapter = RepositoryAdapter()
    private val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = RepositoryfragmentBinding.bind(view)

        lifecycleScope.launchWhenResumed {
            viewModel.getUserRepository()
        }
        initListeners()
        initOnserverd()
    }
    private fun initListeners(){
        binding.recyclerView.adapter = adapter

        binding.apply {
            icBackRepo.setOnClickListener {

            }
        }
    }
    private fun initOnserverd() {
        viewModel.getUserRepositoryFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            Toast.makeText(requireContext(), "Mag'luwmat kelmedi", Toast.LENGTH_SHORT).show()
        }.launchIn(lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visilityOfBottomNavigation(View.GONE)
    }

}