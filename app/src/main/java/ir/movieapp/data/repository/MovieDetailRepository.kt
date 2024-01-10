package ir.movieapp.data.repository

import ir.movieapp.data.TMDBApi
import ir.movieapp.data.remote.response.CreditsResponse
import ir.movieapp.data.remote.response.MovieDetailResponse
import ir.movieapp.util.preview.Resource
import timber.log.Timber
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val api: TMDBApi
) {

    suspend fun getMovieDetail(filmId: Int) :Resource<MovieDetailResponse>{
        val response = try{
            api.getMovieDetail(movieId = filmId)
        }catch (e : Exception){
            return Resource.Error("Unknown error occurred")
        }

        Timber.d("Movie details: $response")
        return Resource.Success(response)
    }

    suspend fun getMovieCredits(filmId: Int) :Resource<CreditsResponse>{
        val response = try{
            api.getCredits(movieId = filmId)
        }catch (e : Exception){
            return Resource.Error("Unknown error occurred")
        }

        Timber.d("Movie details: $response")
        return Resource.Success(response)
    }

}