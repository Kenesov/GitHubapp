package com.example.githubapp.ui.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.githubapp.R
import com.example.githubapp.databinding.ExplorefragmentBinding

class ExploreFragment: Fragment(R.layout.explorefragment) {
    private lateinit var binding: ExplorefragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ExplorefragmentBinding.bind(view)
    }
}