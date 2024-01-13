package ir.movieapp.ui.screens.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.R
import ir.movieapp.ui.screens.commons.MovieItem
import ir.movieapp.ui.screens.destinations.MovieDetailScreenDestination
import ir.movieapp.ui.theme.primaryDark
import ir.movieapp.ui.theme.primaryPink
import ir.movieapp.util.preview.Constants
import ir.movieapp.util.preview.Constants.IMAGE_BASE_URL
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {


    Scaffold(
        topBar = {
            Surface(shadowElevation = 5.dp) {
                TopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = {
                        Column {
                            Image(
                                modifier = Modifier
                                    .size(width = 150.dp, height = 150.dp)
                                    ,
                                painter = painterResource(id = R.drawable.movieapp),
                                contentDescription = "Image",
                            )
                        }
                    },
                    colors = topAppBarColors(containerColor = primaryDark),
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Search, contentDescription = null)
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

            item {
                FilmCategory(
                    listOf("Movies", "Tv Shows"),
                    modifier = Modifier.fillMaxWidth(),
                    viewModel = viewModel
                )


            }

            item {
                Text(
                    text = "Genres",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))
            }

            item {
                Genres(
                    viewModel = viewModel,
                )
            }

            item {
                Text(
                    text = "Trending today",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))
            }

            item {
                TrendingToday(
                    viewModel = viewModel,
                    navigator
                )
            }

            item {
                Text(
                    text = "Popular Movies",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))
            }

            item {
                PopularMovies(
                    viewModel = viewModel,
                    navigator
                )
            }

            item {
                Text(
                    text = if (viewModel.selectedOption.value == Constants.TV_SHOWS) {
                        "On Air"
                    } else {
                        "Upcoming"
                    },
                    modifier = Modifier.padding(8.dp),
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                UpcomingMovies(
                    viewModel = viewModel,
                    navigator
                )
            }

            item {
                Text(
                    text = "Now Playing",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                NowPlayingMovies(
                    viewModel = viewModel,
                    navigator
                )
            }

            item {
                Text(
                    text = "Top Rated",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                TopRatedMovies(
                    viewModel = viewModel,
                    navigator
                )
            }
        }
    }

}

@Composable
fun TopRatedMovies(
    viewModel: HomeViewModel,
    navigator: DestinationsNavigator

) {
    val topRatedMovies = viewModel.topRatedMovies.value.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow{
            items(topRatedMovies.itemCount) { film ->
                Timber.e("TopRatedMovies: %s", topRatedMovies[film]?.posterPath)
                MovieItem(
                    modifier = Modifier
                        .height(200.dp)
                        .width(130.dp)
                        .clickable {
                            navigator.navigate(MovieDetailScreenDestination(topRatedMovies[film]?.id!!))
                        },
                    imageUrl = "$IMAGE_BASE_URL/${topRatedMovies[film]?.posterPath}"
                )
            }

            Timber.e("TopRatedMovies: Loading")
            if (topRatedMovies.loadState.refresh is LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun NowPlayingMovies(
    viewModel: HomeViewModel,
    navigator: DestinationsNavigator

) {
    val nowPlaying = viewModel.nowPlayingMovies.value.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow {
            items(nowPlaying.itemCount) { film ->
                Timber.e("NowPlayingMovies: %s", nowPlaying[film]?.posterPath)
                MovieItem(
                    modifier = Modifier
                        .height(200.dp)
                        .width(130.dp)
                        .clickable {
                            navigator.navigate(MovieDetailScreenDestination(nowPlaying[film]?.id!!))
                        },
                    imageUrl = "$IMAGE_BASE_URL/${nowPlaying[film]?.posterPath}"
                )
            }

            Timber.e("NowPlayingMovies: Loading")
            if (nowPlaying.loadState.refresh is LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun UpcomingMovies(
    viewModel: HomeViewModel,
    navigator: DestinationsNavigator
) {
    val upcomingMovie = viewModel.upcomingMovies.value.collectAsLazyPagingItems()
    val onAirTvSeries = viewModel.onAirTvSeries.value.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow{
            if (viewModel.selectedOption.value == Constants.MOVIES) {
                items(upcomingMovie.itemCount) { film ->
                    Timber.e("UpcomingMovies: %s", upcomingMovie[film]?.posterPath)
                    MovieItem(
                        modifier = Modifier
                            .height(200.dp)
                            .width(130.dp)
                            .clickable {
                                navigator.navigate(MovieDetailScreenDestination(upcomingMovie[film]?.id!!))
                            },
                        imageUrl = "$IMAGE_BASE_URL/${upcomingMovie[film]?.posterPath}"
                    )
                }
            } else {

                items(onAirTvSeries.itemCount) { film ->
                    Timber.e("OnAirTvSeries: %s", onAirTvSeries[film]?.posterPath)
                    MovieItem(
                        modifier = Modifier
                            .height(200.dp)
                            .width(130.dp)
                            .clickable {
                                navigator.navigate(MovieDetailScreenDestination(onAirTvSeries[film]?.id!!))
                            },
                        imageUrl = "$IMAGE_BASE_URL/${onAirTvSeries[film]?.posterPath}"
                    )
                }
            }


            Timber.e("UpcomingMovies: Loading")
            if (upcomingMovie.loadState.refresh is LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun PopularMovies(
    viewModel: HomeViewModel,
    navigator: DestinationsNavigator
) {
    val popularMovie = viewModel.popularMovies.value.collectAsLazyPagingItems()
    val popularTvSeries = viewModel.popularTvSeries.value.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center

    ) {
        LazyRow {
            if (viewModel.selectedOption.value == Constants.MOVIES) {
                items(popularMovie.itemCount) { film ->
                    Timber.e("PopularMovies: %s", popularMovie[film]?.posterPath)
                    MovieItem(
                        modifier = Modifier
                            .height(200.dp)
                            .width(130.dp)
                            .clickable {
                                navigator.navigate(MovieDetailScreenDestination(popularMovie[film]?.id!!))
                            },
                        imageUrl = "$IMAGE_BASE_URL/${popularMovie[film]?.posterPath}"
                    )
                }
            } else {
                items(popularTvSeries.itemCount) { film ->
                    Timber.e("PopularTvSeries: %s", popularTvSeries[film]?.posterPath)
                    MovieItem(
                        modifier = Modifier
                            .height(200.dp)
                            .width(130.dp)
                            .clickable {
                                navigator.navigate(MovieDetailScreenDestination(popularTvSeries[film]?.id!!))
                            },
                        imageUrl = "$IMAGE_BASE_URL/${popularTvSeries[film]?.posterPath}"
                    )
                }
            }

            Timber.e("PopularMovies: Loading")

            if (popularMovie.loadState.refresh is LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally),
                    )
                }
            }
        }
    }
}

@Composable
fun TrendingToday(
    viewModel: HomeViewModel,
    navigator: DestinationsNavigator

) {
    val trendingMovie = viewModel.trendingMovies.value.collectAsLazyPagingItems()
    val trendingTvSeries = viewModel.trendingTvSeries.value.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow{
            if (viewModel.selectedOption.value == Constants.MOVIES) {
                items(trendingMovie.itemCount) { film ->
                    Timber.e("TrendingToday: %s", trendingMovie[film]?.posterPath)
                    MovieItem(
                        modifier = Modifier
                            .height(200.dp)
                            .width(230.dp)
                            .clickable {
                                navigator.navigate(MovieDetailScreenDestination(trendingMovie[film]?.id!!))
                            },
                        imageUrl = "$IMAGE_BASE_URL/${trendingMovie[film]?.posterPath}"
                    )
                }
            } else {
                items(trendingTvSeries.itemCount) { film ->
                    Timber.e("TrendingToday: %s", trendingTvSeries[film]?.posterPath)
                    MovieItem(
                        modifier = Modifier
                            .height(220.dp)
                            .width(250.dp)
                            .clickable {
                                navigator.navigate(MovieDetailScreenDestination(trendingMovie[film]?.id!!))
                            },
                        imageUrl = "$IMAGE_BASE_URL/${trendingTvSeries[film]?.posterPath}"
                    )
                }
            }


            Timber.e("TrendingToday: Loading")
            if (trendingMovie.loadState.refresh is LoadState.Loading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun FilmCategory(items: List<String>, modifier: Modifier, viewModel: HomeViewModel) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        items.forEach { item ->
            val selectedOption = viewModel.selectedOption.value == item
            val lineLength = animateFloatAsState(
                targetValue = if (selectedOption) {
                    2f
                } else {
                    0f
                }, label = ""
            )


            Text(
                text = item,
                color = if (selectedOption) Color.White else Color.Gray,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .drawBehind {
                        if (selectedOption) {
                            if (lineLength.value > 0f) {
                                drawLine(
                                    color = if (selectedOption)
                                        Color.White
                                    else
                                        Color.Transparent,
                                    start = Offset(
                                        x = size.width / 2f - lineLength.value * 10.dp.toPx(),
                                        y = size.height
                                    ),
                                    end = Offset(
                                        x = size.width / 2 + lineLength.value * 10.dp.toPx(),
                                        y = size.height
                                    ),
                                    strokeWidth = 2.dp.toPx(),
                                    cap = StrokeCap.Round
                                )
                            }
                        }
                    }

                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {
                        viewModel.setSelectedOption(item)
                    },
            )
        }
    }
}

@Composable
fun Genres(
    viewModel: HomeViewModel
) {

    val genres = if (viewModel.selectedOption.value == Constants.MOVIES) {
        viewModel.movieGenre.value
    } else {
        viewModel.seriesGenre.value
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        LazyRow {
            items(genres.size) { index ->
                Text(
                    text = genres[index].name,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(
                            RoundedCornerShape(
                                size = 8.dp
                            )
                        )
                        .clickable {
                            if (viewModel.selectedOption.value == Constants.MOVIES) {
                                viewModel.setGenre(genres[index].name)
                                viewModel.getTrendingMovies(genres[index].id)
                                viewModel.getPopularMovies(genres[index].id)
                                viewModel.getUpcomingMovies(genres[index].id)
                                viewModel.getNowPlayingMovies(genres[index].id)
                                viewModel.getTopRatedMovies(genres[index].id)
                            } else {
                                viewModel.setGenre(genres[index].name)
                                viewModel.getTrendingSeries(genres[index].id)
                                viewModel.getPopularTvSeries(genres[index].id)
                                viewModel.getOnAirTvSeries(genres[index].id)
                            }

                        }
                        .background(
                            if (viewModel.selectedGenre.value == genres[index].name) {
                                primaryPink
                            } else {
                                primaryDark
                            }
                        )
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 10.dp,
                            bottom = 10.dp
                        ),
                )
            }
        }
    }
}
