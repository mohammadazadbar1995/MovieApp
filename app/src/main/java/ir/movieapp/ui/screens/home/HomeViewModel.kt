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
import ir.movieapp.data.remote.response.OnAirResponse
import ir.movieapp.data.remote.response.PopularResponse
import ir.movieapp.data.remote.response.TopRatedResponse
import ir.movieapp.data.remote.response.TrendingResponse
import ir.movieapp.data.remote.response.commen.Genre
import ir.movieapp.data.repository.GenreRepository.GenreRepository
import ir.movieapp.data.repository.MoviesRepository
import ir.movieapp.data.repository.NowPlayingResponse
import ir.movieapp.data.repository.TvSeriesRepository
import ir.movieapp.data.repository.UpcomingResponse
import ir.movieapp.util.preview.Constants
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
    private val moviesRepository: MoviesRepository,
    private val seriesRepository: TvSeriesRepository
) : ViewModel() {

    private val _selectedOption = mutableStateOf(Constants.MOVIES)
    val selectedOption: State<String> = _selectedOption


    private val _movieGenre = mutableStateOf<List<Genre>>(emptyList())
    val movieGenre: State<List<Genre>> = _movieGenre

    private val _seriesGenre = mutableStateOf<List<Genre>>(emptyList())
    val seriesGenre: State<List<Genre>> = _seriesGenre

    private var _trendingMovies =
        mutableStateOf<Flow<PagingData<TrendingResponse.Movie>>>(emptyFlow())
    val trendingMovies: State<Flow<PagingData<TrendingResponse.Movie>>> = _trendingMovies

    private var _trendingTvSeries =
        mutableStateOf<Flow<PagingData<TrendingResponse.Movie>>>(emptyFlow())
    val trendingTvSeries: State<Flow<PagingData<TrendingResponse.Movie>>> = _trendingTvSeries

    private var _popularMovies =
        mutableStateOf<Flow<PagingData<PopularResponse.Popular>>>(emptyFlow())
    val popularMovies: State<Flow<PagingData<PopularResponse.Popular>>> = _popularMovies


    private var _popularTvSeries =
        mutableStateOf<Flow<PagingData<PopularResponse.Popular>>>(emptyFlow())
    val popularTvSeries: State<Flow<PagingData<PopularResponse.Popular>>> = _popularTvSeries

    private var _upcomingMovies =
        mutableStateOf<Flow<PagingData<UpcomingResponse.Upcoming>>>(emptyFlow())
    val upcomingMovies: State<Flow<PagingData<UpcomingResponse.Upcoming>>> = _upcomingMovies


    private var _onAirTvSeries =
        mutableStateOf<Flow<PagingData<OnAirResponse.OnAirSeries>>>(emptyFlow())
    val onAirTvSeries: State<Flow<PagingData<OnAirResponse.OnAirSeries>>> = _onAirTvSeries


    private var _nowPlayingMovies =
        mutableStateOf<Flow<PagingData<NowPlayingResponse.NowPlaying>>>(emptyFlow())
    val nowPlayingMovies: State<Flow<PagingData<NowPlayingResponse.NowPlaying>>> = _nowPlayingMovies

    private var _topRatedMovies =
        mutableStateOf<Flow<PagingData<TopRatedResponse.TopRated>>>(emptyFlow())
    val topRatedMovies: State<Flow<PagingData<TopRatedResponse.TopRated>>> = _topRatedMovies

    private val _selectedGenre = mutableStateOf("")
    val selectedGenre: State<String> = _selectedGenre

    init {
        getMoviesGenres()
        getSeriesGenres()

        getTrendingMovies(null)
        getPopularMovies(null)
        getUpcomingMovies(null)
        getNowPlayingMovies(null)
        getTopRatedMovies(null)

        //series
        getTrendingSeries(null)
        getPopularTvSeries(null)
        getOnAirTvSeries(null)
    }


    fun setGenre(name: String) {
        _selectedGenre.value = name
    }

    fun setSelectedOption(item: String) {
        _selectedOption.value = item
    }


    private fun getMoviesGenres() {
        viewModelScope.launch {
            when (val genre = genreRepository.getMoviesGenres()) {
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

    fun getPopularMovies(genreId: Int?) {
        viewModelScope.launch {
            _popularMovies.value = if (genreId != null) {
                moviesRepository.getPopularMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getPopularMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getUpcomingMovies(genreId: Int?) {
        viewModelScope.launch {
            _upcomingMovies.value = if (genreId != null) {
                moviesRepository.getUpcomingMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getUpcomingMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getNowPlayingMovies(genreId: Int?) {
        viewModelScope.launch {
            _nowPlayingMovies.value = if (genreId != null) {
                moviesRepository.getNowPlayingMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getNowPlayingMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getTopRatedMovies(genreId: Int?) {
        viewModelScope.launch {
            _topRatedMovies.value = if (genreId != null) {
                moviesRepository.getTopRatedMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getTopRatedMovies().cachedIn(viewModelScope)
            }
        }
    }


    private fun getSeriesGenres() {
        viewModelScope.launch {
            when (val genre = genreRepository.getSeriesGenres()) {
                is Resource.Success -> {
                    genre.data?.let {
                        _seriesGenre.value = it.genres
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

     fun getTrendingSeries(genreId: Int? = null) {
        viewModelScope.launch {
            _trendingTvSeries.value = if (genreId != null) {
                seriesRepository.getTrendingTvSeries().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                seriesRepository.getTrendingTvSeries().cachedIn(viewModelScope)
            }
        }
    }


    fun getPopularTvSeries(genreId: Int?) {
        viewModelScope.launch {
            _popularTvSeries.value = if (genreId != null) {
                seriesRepository.getPopularTvSeries().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                seriesRepository.getPopularTvSeries().cachedIn(viewModelScope)
            }
        }
    }

    fun getOnAirTvSeries(genreId: Int?) {
        viewModelScope.launch {
            _onAirTvSeries.value = if (genreId != null) {
                seriesRepository.getOnAirTvSeries().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                seriesRepository.getOnAirTvSeries().cachedIn(viewModelScope)
            }
        }
    }

}