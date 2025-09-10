package com.jetpack.composepoc.utils
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jetpack.composepoc.R
import com.jetpack.composepoc.ui.theme.Dimens.dp0
import com.jetpack.composepoc.ui.theme.Dimens.dp70

@Composable
fun LoadAvatar(
    avatarUrl: String,
    profileSize: Dp = dp70,
    padding: Dp = dp0
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(avatarUrl)
            .crossfade(true)
            .build(),
        contentDescription = "GitHub Avatar",
        placeholder = painterResource(R.drawable.ic_launcher_background),
        error = painterResource(R.drawable.ic_launcher_background),
        modifier = Modifier
            .padding(padding)
            .size(profileSize)
            .clip(CircleShape)
    )
}
