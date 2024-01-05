package ir.movieapp.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.movieapp.data.remote.response.GenreResponse
import ir.movieapp.data.remote.response.TrendingResponse
import ir.movieapp.data.repository.GenreRepository.GenreRepository
import ir.movieapp.data.repository.MoviesRepository
import ir.movieapp.util.preview.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val genreRepository: GenreRepository,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _movieGenre = mutableStateOf<List<GenreResponse.Genre>>(emptyList())
    val movieGenre: State<List<GenreResponse.Genre>> = _movieGenre

    private var _trendingMovies =
        mutableStateOf<Flow<PagingData<TrendingResponse.Movie>>>(emptyFlow())
    val trendingMovies: State<Flow<PagingData<TrendingResponse.Movie>>> = _trendingMovies

    init {
        getMoviesGenres()

        getTrendingMovies(null)
    }

    private fun getMoviesGenres() {
        viewModelScope.launch {
            val genre = genreRepository.getMoviesGenres()
            when (genre) {
                is Resource.Success -> {
                    genre.data?.let {
                        _movieGenre.value = it.genres
                    }

                    Timber.e("Resource.Success", genre.data.toString())
                }

                is Resource.Error -> {
                    Timber.e("Resource.Error", genre.message.toString())
                }

                is Resource.Loading -> {

                }
            }

        }
    }


    fun getTrendingMovies(genreId: Int? = null) {
        viewModelScope.launch {
            _trendingMovies.value = if (genreId != null) {
                moviesRepository.getTrendingMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getTrendingMovies().cachedIn(viewModelScope)
            }
        }
    }
}