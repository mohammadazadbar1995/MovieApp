package ir.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.remote.response.TopRatedResponse
import timber.log.Timber

class TopRatedMoviesResource(private val api: TMDBApi) :
    PagingSource<Int, TopRatedResponse.TopRated>() {
    override fun getRefreshKey(state: PagingState<Int, TopRatedResponse.TopRated>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopRatedResponse.TopRated> {
        return try {
            val nextPage = params.key ?: 1
            val topRatedMoviesList = api.getTopRatedMovies(page = nextPage)
            Timber.d("upcoming movies list : ${topRatedMoviesList.results}")
            LoadResult.Page(
                data = topRatedMoviesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (topRatedMoviesList.results.isEmpty()) null else topRatedMoviesList.page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}