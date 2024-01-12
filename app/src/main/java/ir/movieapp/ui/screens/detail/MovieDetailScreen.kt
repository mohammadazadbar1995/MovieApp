package ir.movieapp.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.data.remote.response.CreditsResponse
import ir.movieapp.data.remote.response.MovieDetailResponse
import ir.movieapp.ui.screens.favorite.FavoriteViewModel
import ir.movieapp.util.preview.Resource

@Destination
@Composable
fun MovieDetailScreen(
    filmId: Int,
    navigator: DestinationsNavigator,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {

    val scrollState = rememberLazyListState()

    val details = produceState<Resource<MovieDetailResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getMovieDetails(filmId)
    }.value

    val casts = produceState<Resource<CreditsResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getMovieCredits(filmId)
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (details is Resource.Success) {
            FilmInfoDetail(
                scrollState = scrollState,
                navigator = navigator,
                movieData = details.data!!,
                casts = casts
            )

                MovieBanner(
                    navigator = navigator,
                    movieData = details.data!!,
                    scrollState = scrollState,
                    favoriteViewModel = favoriteViewModel,
                )


        } else {
            CircularProgressIndicator()
        }
    }
}
