package ir.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.paging.TrendingMoviesSource
import ir.movieapp.data.remote.response.TrendingResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: TMDBApi){


   fun getTrendingMovies() : Flow<PagingData<TrendingResponse.Movie>> {
       return Pager(
           config = PagingConfig(enablePlaceholders = false, pageSize = 27),
           pagingSourceFactory = {
               TrendingMoviesSource(api)
           }
       ).flow
   }



}