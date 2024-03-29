package ir.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.paging.movie.NowPlayingMoviesSource
import ir.movieapp.data.paging.movie.PopularMoviesSource
import ir.movieapp.data.paging.movie.TopRatedMoviesResource
import ir.movieapp.data.paging.movie.TrendingMoviesSource
import ir.movieapp.data.paging.movie.UpcomingMoviesSource
import ir.movieapp.data.remote.response.PopularResponse
import ir.movieapp.data.remote.response.TopRatedResponse
import ir.movieapp.data.remote.response.TrendingResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: TMDBApi) {


    fun getTrendingMovies(): Flow<PagingData<TrendingResponse.Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TrendingMoviesSource(api)
            }
        ).flow
    }

    fun getPopularMovies(): Flow<PagingData<PopularResponse.Popular>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                PopularMoviesSource(api)
            }
        ).flow
    }


    fun getUpcomingMovies(): Flow<PagingData<UpcomingResponse.Upcoming>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                UpcomingMoviesSource(api)
            }
        ).flow
    }

    fun getNowPlayingMovies(): Flow<PagingData<NowPlayingResponse.NowPlaying>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                NowPlayingMoviesSource(api)
            }
        ).flow
    }

    fun getTopRatedMovies(): Flow<PagingData<TopRatedResponse.TopRated>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TopRatedMoviesResource(api)
            }
        ).flow
    }

}