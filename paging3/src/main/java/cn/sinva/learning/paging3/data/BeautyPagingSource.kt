package cn.sinva.learning.paging3.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cn.sinva.learning.paging3.api.BeautyBackendService
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
            val nextPageNumber = params.key ?: 1
            val response = backend.searchBeauties(query, nextPageNumber)
            return LoadResult.Page(
                data = response.beauties,
                prevKey = null, // Only paging forward.
                nextKey = response.nextPageNumber
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