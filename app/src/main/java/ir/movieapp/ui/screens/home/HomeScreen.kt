package ir.movieapp.ui.screens.home

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import ir.movieapp.R
import ir.movieapp.ui.screens.commons.MovieItem
import ir.movieapp.ui.theme.primaryDark
import ir.movieapp.ui.theme.primaryPink
import ir.movieapp.util.preview.Constants.IMAGE_BASE_URL
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                                        .size(width = 90.dp, height = 90.dp)
                                        .padding(8.dp),
                                    painter = painterResource(id = R.drawable.muviz),
                                    contentDescription = "Image",
                                    colorFilter = ColorFilter.tint(color = primaryPink)
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
                    )
                }

                item {
                    Text(
                        text = "Upcoming",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 22.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                item {
                    UpcomingMovies(
                        viewModel = viewModel,
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
                    )
                }
            }
        }


    }
}

@Composable
fun TopRatedMovies(viewModel: HomeViewModel) {
    val topRatedMovies = viewModel.topRatedMovies.value.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(topRatedMovies.itemCount) { film ->
                Timber.e("TopRatedMovies: %s", topRatedMovies[film]?.posterPath)
                MovieItem(
                    modifier = Modifier
                        .height(200.dp)
                        .width(130.dp),
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
fun NowPlayingMovies(viewModel: HomeViewModel) {
    val nowPlaying = viewModel.nowPlayingMovies.value.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(nowPlaying.itemCount) { film ->
                Timber.e("NowPlayingMovies: %s", nowPlaying[film]?.posterPath)
                MovieItem(
                    modifier = Modifier
                        .height(200.dp)
                        .width(130.dp),
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
fun UpcomingMovies(viewModel: HomeViewModel) {
    val upcomingMovie = viewModel.upcomingMovies.value.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(upcomingMovie.itemCount) { film ->
                Timber.e("UpcomingMovies: %s", upcomingMovie[film]?.posterPath)
                MovieItem(
                    modifier = Modifier
                        .height(200.dp)
                        .width(130.dp),
                    imageUrl = "$IMAGE_BASE_URL/${upcomingMovie[film]?.posterPath}"
                )
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
fun PopularMovies(viewModel: HomeViewModel) {
    val popularMovie = viewModel.popularMovies.value.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center

    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(popularMovie.itemCount) { film ->
                Timber.e("PopularMovies: %s", popularMovie[film]?.posterPath)
                MovieItem(
                    modifier = Modifier
                        .height(200.dp)
                        .width(130.dp),
                    imageUrl = "$IMAGE_BASE_URL/${popularMovie[film]?.posterPath}"
                )
            }

            Timber.e("PopularMovies: Loading")

            if (popularMovie.loadState.refresh is androidx.paging.LoadState.Loading) {
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
fun TrendingToday(viewModel: HomeViewModel) {
    val trendingMovie = viewModel.trendingMovies.value.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(trendingMovie.itemCount) { film ->
                Timber.e("TrendingToday: %s", trendingMovie[film]?.posterPath)
                MovieItem(
                    modifier = Modifier
                        .height(200.dp)
                        .width(230.dp),
                    imageUrl = "$IMAGE_BASE_URL/${trendingMovie[film]?.posterPath}"
                )
            }

            Timber.e("TrendingToday: Loading")
            if (trendingMovie.loadState.refresh is androidx.paging.LoadState.Loading) {
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
            val lineLength = animateFloatAsState(
                targetValue = if (viewModel.selectedOption.value == item) {
                    2f
                } else {
                    0f
                }, label = ""
            )


            Text(
                text = item,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {

                    },
            )
        }
    }
}

@Composable
fun Genres(
    viewModel: HomeViewModel
) {
    val genres = viewModel.movieGenre.value

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
                            viewModel.setGenre(genres[index].name)
                            viewModel.getTrendingMovies(genres[index].id)
                            viewModel.getPopularMovies(genres[index].id)
                            viewModel.getUpcomingMovies(genres[index].id)
                            viewModel.getNowPlayingMovies(genres[index].id)
                            viewModel.getTopRatedMovies(genres[index].id)
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
