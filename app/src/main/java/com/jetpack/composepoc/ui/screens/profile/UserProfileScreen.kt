package com.jetpack.composepoc.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jetpack.composepoc.R
import com.jetpack.composepoc.data.model.GetUserRepositories
import com.jetpack.composepoc.ui.theme.Dimens
import com.jetpack.composepoc.ui.theme.Dimens.dp16
import com.jetpack.composepoc.utils.ShowErrorEmptyDataCard
import com.jetpack.composepoc.utils.ShowLoader

@Composable
fun UserProfileScreen(
    username: String,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val repos = viewModel.repos.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    val isRefreshing = repos.loadState.refresh is LoadState.Loading
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    LaunchedEffect(username) {
        viewModel.loadRepos(username)
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { repos.refresh() },
    ) {
        when {
            repos.loadState.refresh is LoadState.Error -> {
                ShowErrorEmptyDataCard(stringResource(R.string.failed_to_load_repositories))
            }

            repos.loadState.append is LoadState.Loading -> {
                ShowLoader()
            }

            repos.itemCount == 0 -> {
                ShowErrorEmptyDataCard(stringResource(R.string.no_data_found))
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(dp16),
                    verticalArrangement = Arrangement.spacedBy(Dimens.dp8),
                    state = listState
                ) {
                    items(repos.itemCount) { index ->
                        repos[index]?.let { repo -> RepoItem(repo = repo) }
                    }

                    item {
                        if (repos.loadState.append is LoadState.Loading) {
                            ShowLoader()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun RepoItem(repo: GetUserRepositories) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = repo.name, style = MaterialTheme.typography.titleMedium)
            repo.description?.let {
                Text(text = it, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Stars",
                    tint = Color.Yellow,
                )
                Text(text = "${repo.stars}")
                Spacer(modifier = Modifier.width(dp16))
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = "instead of forks icon",
                )
                Text(text = "${repo.forks}")
            }
        }
    }
}
