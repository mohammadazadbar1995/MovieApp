package ir.movieapp.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.data.remote.response.MovieDetailResponse
import ir.movieapp.util.preview.Resource

@Destination
@Composable
fun MovieDetailScreen(
    filmId: Int,
    navigator: DestinationsNavigator,
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {

    val scrollState = rememberLazyListState()

    val details = produceState<Resource<MovieDetailResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getMovieDetails(filmId)
    }.value


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (details is Resource.Success) {
            MovieBanner(
                navigator = navigator,
                movieData = details.data!!,
                scrollState = scrollState
            )
        } else {
            CircularProgressIndicator()
        }
    }
}
