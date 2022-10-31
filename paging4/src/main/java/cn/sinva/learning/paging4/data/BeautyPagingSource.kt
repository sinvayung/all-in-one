package cn.sinva.learning.paging4.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import cn.sinva.learning.paging4.api.BeautyBackendService
import okio.IOException
import retrofit2.HttpException

class BeautyPagingSource(
    private val backend: BeautyBackendService,
    private val query: String
) : PagingSource<Int, Beauty>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Beauty> {
        try {
            // Start refresh at page 1 if undefined.
            val pageNumber = params.key ?: 1
            Log.e("Sinva", "params.key: ${params.key}, params.loadSize: ${params.loadSize}, pageNumber: $pageNumber")
            val response = backend.searchBeauties1(query, pageNumber, params.loadSize)
            val nextPageNumber = if (response.beauties.size == BeautyConstants.PAGE_SIZE) { pageNumber + 1 } else { null }
            Log.e("Sinva", "response dataCount: ${response.beauties.size}, nextPageNumber: $nextPageNumber")
            return LoadResult.Page(
                data = response.beauties,
                prevKey = null, // Only paging forward.
                // 框架会根据这个值来确定上拉时，是否允许下一次加载（若不为null，下一次load时，params.key的值即为这里的nextKey）
                nextKey = nextPageNumber
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Beauty>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}