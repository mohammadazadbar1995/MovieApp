package ir.movieapp.data.remote.response


import com.google.gson.annotations.SerializedName
import ir.movieapp.data.remote.response.commen.Genre

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)