package com.example.githubapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubapp.MainActivity
import com.example.githubapp.R
import com.example.githubapp.ViewModel.SearchViewModel
import com.example.githubapp.databinding.FragmentSearchRepositoryBinding
import com.example.githubapp.ui.Adapter.RepositoryAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositorySearchFragment: Fragment(R.layout.fragment_search_repository) {

    private lateinit var binding: FragmentSearchRepositoryBinding
    private val adapter = RepositoryAdapter()
    private val navArgs: RepositorySearchFragmentArgs by navArgs()
    private val viewModel by viewModel<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchRepositoryBinding.bind(view)

        val searchValue = navArgs.textSearch

        lifecycleScope.launchWhenResumed {
            viewModel.searchRepository(searchValue)
        }
        initListeners()
        initObserver()
    }

    private fun initListeners() {
        binding.recyclerView.adapter = adapter
        binding.apply {
            icBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
    private fun initObserver(){
        viewModel.searchRepositoryFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visilityOfBottomNavigation(View.GONE)
    }
}