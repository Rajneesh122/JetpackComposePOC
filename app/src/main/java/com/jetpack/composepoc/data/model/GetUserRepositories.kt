package com.jetpack.composepoc.data.model

data class GetUserRepositories(
    val id: Int,
    val name: String,
    val description: String?,
    val stars: Int,
    val forks: Int
)
