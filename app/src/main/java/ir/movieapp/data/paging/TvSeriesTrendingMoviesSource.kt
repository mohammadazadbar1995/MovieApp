package ir.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.remote.response.TrendingResponse
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class TvSeriesTrendingMoviesSource(private val api: TMDBApi) : PagingSource<Int, TrendingResponse.Movie>() {

    override fun getRefreshKey(state: PagingState<Int, TrendingResponse.Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TrendingResponse.Movie> {
        return try {
            val nextPage = params.key ?: 1
            val trendingMoviesList = api.getTrendingTvSeries(page = nextPage)
            Timber.d("trending movies list : ${trendingMoviesList.results}")
            LoadResult.Page(
                data = trendingMoviesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (trendingMoviesList.results.isEmpty()) null else trendingMoviesList.page!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


}