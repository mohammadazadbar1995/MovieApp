package ir.movieapp.ui.screens.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.data.remote.response.MovieDetailResponse
import ir.movieapp.ui.screens.commons.CircularBackButtons
import ir.movieapp.ui.screens.commons.CircularFavoriteButtons
import ir.movieapp.ui.screens.commons.VoteAverageRatingIndicator
import ir.movieapp.ui.theme.AppBarCollapsedHeight
import ir.movieapp.ui.theme.AppBarExpendedHeight
import ir.movieapp.ui.theme.primaryDark
import ir.movieapp.util.preview.Constants
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieBanner(
    scrollState: LazyListState,
    navigator: DestinationsNavigator,
    movieData: MovieDetailResponse?
) {

    val context = LocalContext.current
    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

    val maxOffset = with(LocalDensity.current) {
        imageHeight.roundToPx()
    } - LocalWindowInsets.current.systemBars.layoutInsets.top


    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset


    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppBarExpendedHeight)
                .safeGesturesPadding(),
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${Constants.IMAGE_BASE_URL}/${movieData?.posterPath}")
                        .crossfade(true)
                        .build(),
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetProgress
                    },
                contentScale = ContentScale.Crop,
                contentDescription = "Movie Banner"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(vertical = 16.dp, horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CircularBackButtons(
                    onClick = {
                        navigator.popBackStack()
                    }
                )

                var isLink by remember {
                    mutableStateOf(
                        true
                    )
                }
                CircularFavoriteButtons(
                    isLiked = isLink,
                    onClick = {
                        if (it) {
                            Toast.makeText(
                                context,
                                "Added to your favorites",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Removed from your favorites",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        isLink = it
                    }
                )


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,

                ) {
                Text(
                    text = movieData?.title ?: "",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    )

                VoteAverageRatingIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.17f),
                    percentage = movieData?.voteAverage?.toFloat() ?: 0f
                )

            }

        }


    }
}