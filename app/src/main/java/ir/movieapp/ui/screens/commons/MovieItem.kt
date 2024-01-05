package ir.movieapp.ui.screens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ir.movieapp.R

@Composable
fun MovieItem(
    modifier: Modifier,
    imageUrl: String,
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .crossfade(true)
                    .build(),
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = modifier
        )
    }
}