package ir.movieapp.ui.screens.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.movieapp.data.remote.response.CreditsResponse
import ir.movieapp.data.remote.response.MovieDetailResponse
import ir.movieapp.data.remote.response.commen.Genre
import ir.movieapp.data.repository.MovieDetailRepository
import ir.movieapp.util.preview.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) : ViewModel() {

    private val _movieDetail = mutableStateOf<MovieDetailResponse?>(null)
    val movieDetail: State<MovieDetailResponse?> = _movieDetail


    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailResponse> {
        return movieDetailRepository.getMovieDetail(movieId)
    }

    suspend fun getMovieCredits(movieId: Int): Resource<CreditsResponse> {
        return movieDetailRepository.getMovieCredits(movieId)
    }


    /*    suspend fun getMovieDetail(movieId: Int) {
            viewModelScope.launch {
                when (val result = movieDetailRepository.getMovieDetail(movieId)) {
                    is Resource.Success -> {
                        _movieDetail.value = result.data!!
                    }
                    is Resource.Error -> {
                        //loadingError.value = result.message.toString()
                    }

                    is Resource.Loading -> {
                        //loading.value = true
                    }

                    else -> {}
                }
            }
        }*/


}