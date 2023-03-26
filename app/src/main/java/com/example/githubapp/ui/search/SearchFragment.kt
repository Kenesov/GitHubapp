package com.example.githubapp.ui.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubapp.MainActivity
import com.example.githubapp.R
import com.example.githubapp.ViewModel.SearchViewModel
import com.example.githubapp.databinding.SearchfragmentBinding
import com.example.githubapp.ui.Adapter.RepositoryAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.searchfragment) {
    private lateinit var binding: SearchfragmentBinding
    private val adapter = RepositoryAdapter()
    private val viewModel by viewModel<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = SearchfragmentBinding.bind(view)

    }

    private fun initListener() {
        binding.apply {
            linearSearch1.visibility = View.GONE
            linearSearch2.visibility = View.GONE
            icBack.setOnClickListener {
                findNavController().popBackStack()
            }

            searchMenu.addTextChangedListener {
                val searchMenu = it.toString()
                linearSearch1.visibility = View.VISIBLE
                linearSearch2.visibility = View.VISIBLE

                linearSearch1.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToRepositorySearchFragment(searchMenu)
                    )
                }
                linearSearch2.setOnClickListener {
                    findNavController().navigate(
                        SearchFragmentDirections.actionSearchFragmentToUsernameSearchFragment(searchMenu)
                    )
                }
            }

        }
    }
    private fun iniObserver(){
         viewModel.searchRepositoryFlow.onEach {
             adapter.submitList(it)
         }.launchIn(lifecycleScope)

        viewModel.messageFlow.onEach {
            Toast.makeText(requireContext(), "Mag'luwmat kelmedi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visilityOfBottomNavigation(View.GONE)
    }


}