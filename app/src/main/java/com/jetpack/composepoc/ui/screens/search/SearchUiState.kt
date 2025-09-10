package com.jetpack.composepoc.ui.screens.search

import com.jetpack.composepoc.data.model.GetGitHubUserList

data class SearchUiState(
    val isLoading: Boolean = false,
    val user: GetGitHubUserList? = null,
    val errorMessage: String? = null
)
