package cn.sinva.learning.paging2.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cn.sinva.learning.paging2.api.PicApi
import cn.sinva.learning.paging2.vo.Pic
import okio.IOException
import retrofit2.HttpException
import java.io.IOError

class PageKeyedPicPagingSource(
    private val picApi: PicApi,
    private val queryText: String
): PagingSource<Int, Pic>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pic> {
        return try {
            val result = picApi.getPics(
                queryText = queryText,
                after = if (params is LoadParams.Append) params.key else null,
                before = if (params is LoadParams.Prepend) params.key else null,
                limit = params.loadSize
            )
            LoadResult.Page(
                data = result.data,
                prevKey = result.before,
                nextKey = result.after
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pic>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}