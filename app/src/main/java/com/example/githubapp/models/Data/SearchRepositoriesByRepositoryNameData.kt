package com.example.githubapp.models.Data

data class SearchRepositoriesByRepositoryNameData(
    val total_count:Int,
    val incomplete_results:Boolean,
    val items: List<GetUserRepositoriesData>
)


