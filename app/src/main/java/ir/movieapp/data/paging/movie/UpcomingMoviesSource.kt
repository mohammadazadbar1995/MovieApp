package ir.movieapp.data.paging.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.repository.UpcomingResponse
import timber.log.Timber

class UpcomingMoviesSource(private val api: TMDBApi) :PagingSource<Int, UpcomingResponse.Upcoming>(){
    override fun getRefreshKey(state: PagingState<Int, UpcomingResponse.Upcoming>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingResponse.Upcoming> {
        return try {
            val nextPage = params.key ?: 1
            val upcomingMoviesList = api.getUpcomingMovies(page = nextPage)
            Timber.d("upcoming movies list : ${upcomingMoviesList.results}")
            LoadResult.Page(
                data = upcomingMoviesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (upcomingMoviesList.results.isEmpty()) null else upcomingMoviesList.page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


}