package com.example.githubapp.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubapp.R
import com.example.githubapp.ViewModel.MainViewModel
import com.example.githubapp.databinding.LoginfragmentBinding
import com.example.githubapp.models.Local.LocalStorage
import com.example.githubapp.utils.toast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment: Fragment(R.layout.loginfragment) {
    private lateinit var binding: LoginfragmentBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = LoginfragmentBinding.bind(view)

        initListeners()
    }

    private fun initListeners() {
        binding.apply {

            btnSignIn.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/login/oauth/authorize?client_id=8f3cf5f09bd0c93a0528&scope=repo")
                )
                startActivity(intent)
            }
            }
        }


    override fun onResume() {
        super.onResume()
        val uri: Uri? = requireActivity().intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                toast("Token kelmedi: $code")
                lifecycleScope.launchWhenResumed {
                    viewModel.getAccessTokenFlow
                }
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                )
            }else if ((uri.getQueryParameter("error")) != null){
                 toast("Something went wrong!")
            }
            initObservers()
        }
    }

  private fun initObservers(){
      viewModel.getAccessTokenFlow.onEach {
          LocalStorage().isLogin = true
          LocalStorage().token = it.access_token
      }.launchIn(lifecycleScope)

      viewModel.messageFlow.onEach {
          toast("Token kelmey qaldi")
      }.launchIn(lifecycleScope)

  }

}


