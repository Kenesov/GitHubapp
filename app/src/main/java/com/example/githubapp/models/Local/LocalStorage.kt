package com.example.githubapp.models.Local

import android.content.Context
import android.content.SharedPreferences
import com.example.chatappwithfirebase.utils.BooleanPreference
import com.example.chatappwithfirebase.utils.StringPreference
import com.example.githubapp.App.App

class LocalStorage {

    companion object{
        val prefs: SharedPreferences =
            App.instance.getSharedPreferences("GithubAppSharedPrefs", Context.MODE_PRIVATE)
    }
    var token by StringPreference(prefs)

    var isLogin by BooleanPreference(prefs)
}