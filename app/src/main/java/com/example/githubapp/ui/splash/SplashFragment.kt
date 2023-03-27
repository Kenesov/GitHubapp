package com.example.githubapp.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubapp.MainActivity
import com.example.githubapp.R
import com.example.githubapp.databinding.SplashfragmentBinding
import com.example.githubapp.models.Local.LocalStorage
import kotlinx.coroutines.delay

class SplashFragment : Fragment(R.layout.splashfragment) {

    private lateinit var binding: SplashfragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = SplashfragmentBinding.bind(view)

        if (LocalStorage().isLogin) {
            lifecycleScope.launchWhenResumed {
                delay(200)
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                )
            }
        }else {
            lifecycleScope.launchWhenResumed {
                delay(200)
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                )
            }
        }

    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).visilityOfBottomNavigation(View.GONE)
    }
    }
