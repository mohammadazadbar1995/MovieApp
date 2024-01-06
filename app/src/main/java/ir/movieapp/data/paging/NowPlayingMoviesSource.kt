package ir.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.movieapp.data.TMDBApi
import ir.movieapp.data.repository.NowPlayingResponse
import timber.log.Timber

class NowPlayingMoviesSource(private val api: TMDBApi) :
    PagingSource<Int, NowPlayingResponse.NowPlaying>() {
    override fun getRefreshKey(state: PagingState<Int, NowPlayingResponse.NowPlaying>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NowPlayingResponse.NowPlaying> {
        return try {
            val nextPage = params.key ?: 1
            val nowPlayingMovies = api.getNowPlayingMovies(page = nextPage)
            Timber.d("upcoming movies list : ${nowPlayingMovies.results}")
            LoadResult.Page(
                data = nowPlayingMovies.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nowPlayingMovies.results.isEmpty()) null else nowPlayingMovies.page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}