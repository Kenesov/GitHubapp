package com.example.githubapp.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.ViewModel.MainViewModel
import com.example.githubapp.databinding.ProfilefragmentBinding
import com.example.githubapp.ui.Adapter.RepositoryAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.profilefragment) {
    private lateinit var binding: ProfilefragmentBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ProfilefragmentBinding.bind(view)

        lifecycleScope.launchWhenResumed {
            viewModel.getUserPropile()
            viewModel.getUserRepository()
        }

        initListener()
        initObservers()
    }

    private fun initListener() {
        binding.apply {

            fouthContainer.setOnClickListener {
              findNavController().navigate(
                  ProfileFragmentDirections.actionProfileFragmentToRepoProfileFragment()
              )
            }

        }
    }

    private fun initObservers() {
        viewModel.getUserPropileFlow.onEach {
            binding.apply {
                proSurname.text = it.name
                proSurname.text = it.login
                followers.text = it.followers.toString()
                following.text = it.following.toString()
                repoNomber.text = it.public_repos.toString()
                Glide.with(this@ProfileFragment)
                    .load(it.avatar_url)
                    .into(profileImg)
            }
        }.launchIn(lifecycleScope)

        viewModel.getUserRepositoryFlow.onEach {

        }
    }


}