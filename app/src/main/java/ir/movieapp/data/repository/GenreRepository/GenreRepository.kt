package ir.movieapp.data.repository.GenreRepository

import ir.movieapp.data.TMDBApi
import ir.movieapp.data.remote.response.GenreResponse
import ir.movieapp.util.preview.Resource
import timber.log.Timber
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val api: TMDBApi
) {

    suspend fun getMoviesGenres(): Resource<GenreResponse> {
        val response = try {
            api.getMovieGenres()
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Movies genres: $response")
        return Resource.Success(response)
    }

}