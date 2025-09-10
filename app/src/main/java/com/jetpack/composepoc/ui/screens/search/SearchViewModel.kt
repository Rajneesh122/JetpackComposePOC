package com.jetpack.composepoc.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.composepoc.data.Result
import com.jetpack.composepoc.data.repository.GitHubRepository
import com.jetpack.composepoc.utils.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _username = MutableStateFlow(EMPTY_STRING)
    val username: StateFlow<String> = _username.asStateFlow()

    fun updateUsername(newName: String) {
        _username.value = newName
    }

    fun searchUser(username: String) {
        viewModelScope.launch {
            _uiState.value = SearchUiState(isLoading = true)
            when (val result = repository.getUser(username)) {
                is Result.Error.NetworkError -> {
                    _uiState.value = SearchUiState(errorMessage = result.message)
                }

                is Result.Error.NotFoundError -> {
                    _uiState.value = SearchUiState(errorMessage = result.message)
                }

                is Result.Error.TimeoutError -> {
                    _uiState.value = SearchUiState(errorMessage = result.message)
                }

                is Result.Error.UnknownError -> {
                    _uiState.value = SearchUiState(errorMessage = result.message)
                }

                is Result.Success -> {
                    _uiState.value = SearchUiState(user = result.data)
                }
            }
        }
    }
}
