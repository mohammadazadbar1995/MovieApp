package ir.movieapp.data

import ir.movieapp.data.remote.response.GenreResponse
import ir.movieapp.util.preview.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): GenreResponse
}