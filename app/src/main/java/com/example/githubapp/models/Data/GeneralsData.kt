package com.example.githubapp.models.Data

data class GeneralsData<out T>(
    val success:Boolean,
    val code: Int,
    val message: String,
    val payload: T
)
