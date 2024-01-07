package ir.movieapp.data.paging.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.remote.response.PopularResponse
import timber.log.Timber

class PopularMoviesSource(private val api: TMDBApi) : PagingSource<Int, PopularResponse.Popular>() {

    override fun getRefreshKey(state: PagingState<Int, PopularResponse.Popular>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularResponse.Popular> {
        return try {
            val nextPage = params.key ?: 1
            val popularMoviesList = api.getPopularMovies(page = nextPage)
            Timber.d("popular movies list : ${popularMoviesList.results}")
            LoadResult.Page(
                data = popularMoviesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (popularMoviesList.results.isEmpty()) null else popularMoviesList.page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}