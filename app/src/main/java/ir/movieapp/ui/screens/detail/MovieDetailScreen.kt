package ir.movieapp.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.movieapp.util.preview.Resource

@Composable
fun MovieDetailScreen(
    filmId: Int,
    navigator: DestinationsNavigator,
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {

    val details = viewModel.movieDetail.value

    Box {

    }
}
