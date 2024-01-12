package ir.movieapp.ui.screens.favorite

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.R
import ir.movieapp.data.local.Favorite
import ir.movieapp.ui.screens.commons.VoteAverageRatingIndicator
import ir.movieapp.ui.screens.home.FilmCategory
import ir.movieapp.ui.screens.home.Genres
import ir.movieapp.ui.screens.home.NowPlayingMovies
import ir.movieapp.ui.screens.home.PopularMovies
import ir.movieapp.ui.screens.home.TopRatedMovies
import ir.movieapp.ui.screens.home.TrendingToday
import ir.movieapp.ui.screens.home.UpcomingMovies
import ir.movieapp.ui.theme.Transparent
import ir.movieapp.ui.theme.primaryDark
import ir.movieapp.ui.theme.primaryPink
import ir.movieapp.util.preview.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun FavoriteScreen(
    navigator: DestinationsNavigator,
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    val favorites = viewModel.favorites.observeAsState()

    Scaffold(
        topBar = {
            Surface(shadowElevation = 5.dp) {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = {
                        Text(
                            text = "Favorite",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold

                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = primaryDark),
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Delete, contentDescription = null)
                        }
                    },
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize()
        ) {
            favorites.value?.let {
                items(it.size) { index ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                            .clickable {
//                                if (favorite.mediaType == "movie") {
//                                    navigator.navigate(MovieDetailsScreenDestination(favorite.mediaId))
//                                }
                            }
                    ) {
                        FavoriteItem(
                            favorite = favorites.value!![index],
                        )
                    }
                    val favorite = it[index]
                    FavoriteItem(favorite = favorite)
                }
            }

        }
    }
}

@Composable
fun FavoriteItem(favorite: Favorite) {
    Box {
        Image(
            painter = rememberImagePainter(
                data = favorite.image,
                builder = {
                    placeholder(R.drawable.ic_placeholder)
                    crossfade(true)
                }
            ),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = "Movie Banner"
        )
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    Brush.verticalGradient(
//                        colorStops = arrayOf(
//                            Pair(0.3f, Transparent),
//                            Pair(1.5f, primaryDark)
//                        )
//                    )
//                )
//        )

        FilmDetail(
            favorite = favorite
        )
    }
}

@Composable
fun FilmDetail(favorite: Favorite) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = favorite.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = favorite.releaseDate,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        VoteAverageRatingIndicator(
            percentage = favorite.rating
        )

    }
}
