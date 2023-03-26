package com.example.githubapp.models.Data

data class SearchUsersByUsernameData(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<ItemsData>

)
