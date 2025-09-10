package com.jetpack.composepoc.data.model

data class GetGitHubUserList(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val bio: String?,
    val followers: Int,
    val public_repos: Int
)

