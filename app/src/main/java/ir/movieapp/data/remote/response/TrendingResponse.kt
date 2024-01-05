package ir.movieapp.data.remote.response


import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    data class Movie(
        @SerializedName("adult")
        val adult: Boolean?= null,
        @SerializedName("backdrop_path")
        val backdropPath: String?= null,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int?= null,
        @SerializedName("media_type")
        val mediaType: String?= null,
        @SerializedName("original_language")
        val originalLanguage: String?= null,
        @SerializedName("original_title")
        val originalTitle: String?= null,
        @SerializedName("overview")
        val overview: String?= null,
        @SerializedName("popularity")
        val popularity: Double?= null,
        @SerializedName("poster_path")
        val posterPath: String?= null,
        @SerializedName("release_date")
        val releaseDate: String?= null,
        @SerializedName("title")
        val title: String?= null,
        @SerializedName("video")
        val video: Boolean?= null,
        @SerializedName("vote_average")
        val voteAverage: Double?= null,
        @SerializedName("vote_count")
        val voteCount: Int? = null
    )
}