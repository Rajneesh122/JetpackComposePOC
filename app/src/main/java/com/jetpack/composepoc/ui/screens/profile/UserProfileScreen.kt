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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetpack.composepoc.ui.theme.Dimens

@Composable
fun UserProfileScreen(
    username: String,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(Dimens.dp16),
        verticalArrangement = Arrangement.spacedBy(Dimens.dp8)
    ) {
        items(10) { index ->
            RepoItem()
        }
    }
}


@Composable
fun RepoItem() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "dfdf", style = MaterialTheme.typography.titleMedium)

            Text(text = "ffdfdsf sfsdfs sfsdf", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Stars",
                tint = Color.Yellow
            )
            Text(text = "3")
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Filled.List, contentDescription = "Forks")
            Text(text = "11")
        }
    }
}
