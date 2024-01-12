package ir.movieapp.ui.screens.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun FavoriteScreen(
    navigator: DestinationsNavigator,
    viewModel: FavoriteViewModel = hiltViewModel()
) {

}