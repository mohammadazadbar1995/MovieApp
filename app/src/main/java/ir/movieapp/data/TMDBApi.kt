package ir.movieapp.data

import ir.movieapp.data.remote.response.CreditsResponse
import ir.movieapp.data.remote.response.GenreResponse
import ir.movieapp.data.remote.response.MovieDetailResponse
import ir.movieapp.data.remote.response.OnAirResponse
import ir.movieapp.data.remote.response.PopularResponse
import ir.movieapp.data.remote.response.TopRatedResponse
import ir.movieapp.data.remote.response.TrendingResponse
import ir.movieapp.data.repository.NowPlayingResponse
import ir.movieapp.data.repository.UpcomingResponse
import ir.movieapp.util.preview.Constants.API_KEY
import ir.movieapp.util.preview.Constants.STARTING_PAGE_INDEX
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): GenreResponse


    @GET("trending/movie/day")
    suspend fun getTrendingMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = STARTING_PAGE_INDEX
    ): TrendingResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = STARTING_PAGE_INDEX
    ): PopularResponse


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = STARTING_PAGE_INDEX
    ): UpcomingResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = STARTING_PAGE_INDEX
    ): NowPlayingResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = STARTING_PAGE_INDEX
    ): TopRatedResponse


    @GET("genre/tv/list")
    suspend fun getTvSeriesGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): GenreResponse

    @GET("trending/tv/day")
    suspend fun getTrendingTvSeries(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): TrendingResponse

    @GET("tv/popular")
    suspend fun getPopularTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = STARTING_PAGE_INDEX
    ): PopularResponse

    @GET("tv/on_the_air")
    suspend fun getOnAirTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = STARTING_PAGE_INDEX
    ): OnAirResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
    ): MovieDetailResponse


    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en",
    ): CreditsResponse
}