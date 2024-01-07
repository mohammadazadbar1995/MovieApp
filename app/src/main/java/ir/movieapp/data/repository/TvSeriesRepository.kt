package ir.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.paging.movie.NowPlayingMoviesSource
import ir.movieapp.data.paging.movie.PopularMoviesSource
import ir.movieapp.data.paging.movie.TopRatedMoviesResource
import ir.movieapp.data.paging.series.TrendingTvSeriesSource
import ir.movieapp.data.paging.movie.UpcomingMoviesSource
import ir.movieapp.data.paging.series.OnAirTvSeriesSource
import ir.movieapp.data.paging.series.PopularTvSeriesSource
import ir.movieapp.data.remote.response.OnAirResponse
import ir.movieapp.data.remote.response.PopularResponse
import ir.movieapp.data.remote.response.TopRatedResponse
import ir.movieapp.data.remote.response.TrendingResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvSeriesRepository @Inject constructor(private val api: TMDBApi) {


    fun getTrendingTvSeries(): Flow<PagingData<TrendingResponse.Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TrendingTvSeriesSource(api)
            }
        ).flow
    }

    fun getPopularTvSeries(): Flow<PagingData<PopularResponse.Popular>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                PopularTvSeriesSource(api)
            }
        ).flow
    }


    fun getOnAirTvSeries(): Flow<PagingData<OnAirResponse.OnAirSeries>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                OnAirTvSeriesSource(api)
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