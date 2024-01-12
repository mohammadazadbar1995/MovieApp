package ir.movieapp.ui.screens.commons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.movieapp.R

@Composable
fun CircularFavoriteButtons(
    isLiked: Boolean,
    onClick: (isFavorite: Boolean) -> Unit = {}
) {
    IconButton(
        onClick = {
            onClick(isLiked)
        }
    ) {
        Icon(
            modifier = Modifier
                .width(38.dp)
                .height(38.dp),
            imageVector = if (isLiked) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            tint = if (isLiked) {
                Color.Red
            } else {
                Color.LightGray
            },

            contentDescription = if (isLiked) {
                stringResource(id = R.string.unlike)
            } else {
                stringResource(id = R.string.like)
            }
        )
    }

}