package ir.movieapp.data.paging.series

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.remote.response.PopularResponse
import timber.log.Timber

class PopularTvSeriesSource(private val api: TMDBApi) : PagingSource<Int, PopularResponse.Popular>() {

    override fun getRefreshKey(state: PagingState<Int, PopularResponse.Popular>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularResponse.Popular> {
        return try {
            val nextPage = params.key ?: 1
            val popularTvSeriesList = api.getPopularTvSeries(page = nextPage)
            Timber.d("popular movies list : ${popularTvSeriesList.results}")
            LoadResult.Page(
                data = popularTvSeriesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (popularTvSeriesList.results.isEmpty()) null else popularTvSeriesList.page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}