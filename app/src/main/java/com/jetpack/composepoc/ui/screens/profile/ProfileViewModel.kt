package com.jetpack.composepoc.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jetpack.composepoc.data.model.GetUserRepositories
import com.jetpack.composepoc.data.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _repos = MutableStateFlow<PagingData<GetUserRepositories>>(PagingData.empty())
    val repos: StateFlow<PagingData<GetUserRepositories>> = _repos.asStateFlow()

    fun loadRepos(username: String) {
        repository.getReposPager(username)
            .cachedIn(viewModelScope)
            .onEach { pagingData ->
                _repos.value = pagingData
            }
            .launchIn(viewModelScope)
    }
}
