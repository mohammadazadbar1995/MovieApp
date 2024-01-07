package ir.movieapp.data.paging.series

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.remote.response.OnAirResponse
import ir.movieapp.data.repository.UpcomingResponse
import timber.log.Timber

class OnAirTvSeriesSource(private val api: TMDBApi) :PagingSource<Int, OnAirResponse.OnAirSeries>(){
    override fun getRefreshKey(state: PagingState<Int, OnAirResponse.OnAirSeries>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OnAirResponse.OnAirSeries> {
        return try {
            val nextPage = params.key ?: 1
            val onAirTvSeriesList = api.getOnAirTvSeries(page = nextPage)
            Timber.d("onAir Tv List : ${onAirTvSeriesList.results}")
            LoadResult.Page(
                data = onAirTvSeriesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (onAirTvSeriesList.results.isEmpty()) null else onAirTvSeriesList.page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


}