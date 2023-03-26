package com.example.githubapp.ui.notification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.githubapp.R
import com.example.githubapp.databinding.NotificationsfragmentBinding

class NotificationFragment: Fragment(R.layout.notificationsfragment) {
    private lateinit var binding: NotificationsfragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = NotificationsfragmentBinding.bind(view)

    }


}