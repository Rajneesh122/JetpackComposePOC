package com.jetpack.composepoc.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jetpack.composepoc.R
import com.jetpack.composepoc.ui.theme.Dimens

@Composable
fun ShowErrorEmptyDataCard(message: String = stringResource(R.string.no_data_found)) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.padding(Dimens.dp16),
            elevation = CardDefaults.cardElevation(defaultElevation = Dimens.dp8)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(Dimens.dp24),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
