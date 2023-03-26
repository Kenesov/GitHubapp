package com.example.githubapp.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubapp.MainActivity
import com.example.githubapp.R
import com.example.githubapp.ViewModel.SearchViewModel
import com.example.githubapp.databinding.SearchUserNameFragmentBinding
import com.example.githubapp.ui.Adapter.UsernameSearchAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsernameSearchFragment : Fragment(R.layout.search_user_name_fragment) {
    private val adapter = UsernameSearchAdapter()
    private val viewModel by viewModel<SearchViewModel>()
    private val navArgs: RepositorySearchFragmentArgs by navArgs()
    private lateinit var binding: SearchUserNameFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = SearchUserNameFragmentBinding.bind(view)

        val login = navArgs.textSearch

        lifecycleScope.launchWhenResumed {
            viewModel.searchUser(login)
        }

        initListeners()
        initOnserver()
    }

    private fun initListeners() {
        binding.recyclerView.adapter = adapter

        binding.apply {
            icBackUserSearch.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initOnserver() {
        viewModel.searchUserFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visilityOfBottomNavigation(View.GONE)
    }

}