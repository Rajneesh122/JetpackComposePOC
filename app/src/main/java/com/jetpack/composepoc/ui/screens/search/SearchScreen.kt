package com.jetpack.composepoc.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jetpack.composepoc.R
import com.jetpack.composepoc.data.model.GetGitHubUserList
import com.jetpack.composepoc.ui.theme.Dimens
import com.jetpack.composepoc.utils.ShowLoader
import com.jetpack.composepoc.utils.USER_PROFILE
import com.jetpack.composepoc.utils.LoadAvatar

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val username by viewModel.username.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.dp16)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { viewModel.updateUsername(it) },
            label = { Text(stringResource(R.string.github_username)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(Dimens.dp8))
        Button(
            onClick = { viewModel.searchUser(username) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.search))
        }
        Spacer(modifier = Modifier.height(Dimens.dp16))
        when {
            uiState.isLoading -> {
                ShowLoader()
            }

            uiState.errorMessage != null -> {
                Text(
                    text = uiState.errorMessage ?: stringResource(R.string.user_not_found),
                    color = MaterialTheme.colorScheme.error
                )
            }

            uiState.user != null -> {
                UserProfileCard(user = uiState.user!!) {
                    navController.navigate("$USER_PROFILE/${uiState.user!!.login}")
                }
            }
        }
    }
}

@Composable
fun UserProfileCard(user: GetGitHubUserList, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.dp8)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation()
    ) {
        Row(modifier = Modifier.padding(Dimens.dp16)) {
            LoadAvatar(user.avatar_url)
            Spacer(modifier = Modifier.width(Dimens.dp16))
            Column {
                Text(text = user.login, style = MaterialTheme.typography.titleMedium)
                user.bio?.let { Text(text = it) }
                Text(text = "${stringResource(R.string.followers)}: ${user.followers}")
                Text(text = "${stringResource(R.string.repos)}: ${user.public_repos}")
            }
        }
    }
}
