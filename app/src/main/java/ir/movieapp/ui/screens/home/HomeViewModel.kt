package ir.movieapp.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.movieapp.data.remote.response.GenreResponse
import ir.movieapp.data.repository.GenreRepository.GenreRepository
import ir.movieapp.util.preview.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
) : ViewModel() {

    private val _movieGenre = mutableStateOf<List<GenreResponse.Genre>>(emptyList())
    val movieGenre: State<List<GenreResponse.Genre>> = _movieGenre


    init {
        getMoviesGenres()
    }
    private fun getMoviesGenres() {
        viewModelScope.launch {
            val genre = genreRepository.getMoviesGenres()
            when (genre) {
                is Resource.Success -> {
                    genre.data?.let {
                        _movieGenre.value = it.genres
                    }
                }

                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }
            }

        }
    }
}