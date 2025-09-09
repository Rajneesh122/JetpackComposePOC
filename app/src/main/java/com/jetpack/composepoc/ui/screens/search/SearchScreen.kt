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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jetpack.composepoc.R
import com.jetpack.composepoc.ui.theme.Dimens

@Composable
fun SearchScreen(
    navController: NavController,
) {
    val username by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.dp16)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = {  },
            label = { Text(text = "Text") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(Dimens.dp8))
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.search))
        }
        Spacer(modifier = Modifier.height(Dimens.dp16))
    }
}

@Composable
fun UserProfileCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.dp8)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation()
    ) {
        Row(modifier = Modifier.padding(Dimens.dp16)) {
            Spacer(modifier = Modifier.width(Dimens.dp16))
            Column {
                Text(text = "Rajneesh", style = MaterialTheme.typography.titleMedium)
                Text(text = "Vinayak")
                Text(text = "${stringResource(R.string.followers)}: 999")
                Text(text = "${stringResource(R.string.repos)}: 4")
            }
        }
    }
}
