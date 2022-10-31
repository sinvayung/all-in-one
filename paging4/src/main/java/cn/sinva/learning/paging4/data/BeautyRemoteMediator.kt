package cn.sinva.learning.paging4.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import cn.sinva.learning.paging4.api.BeautyBackendService
import cn.sinva.learning.paging4.dao.BeautyDatabase
import okio.IOException
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class BeautyRemoteMediator(
    private val query: String,
    private val database: BeautyDatabase,
    private val beautyBackendService: BeautyBackendService
) : RemoteMediator<Int, Beauty>() {

    private val beautyDao = database.beautyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Beauty>
    ): MediatorResult {
        return try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> null

                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                     lastItem.id
                }
            }
            val fromId = loadKey?:1
            Log.e("Sinva", "loadType: $loadType, loadKey: $loadKey, fromId: $fromId")
            val response = beautyBackendService.searchBeauties2(query, fromId, state.config.pageSize)
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    beautyDao.clearAll()
                }
                beautyDao.insertAll(response.beauties)
            }
            val endOfPaginationReached = response.beauties.size < state.config.pageSize
            Log.e("Sinva", "response.beauties.size: ${response.beauties.size}, endOfPaginationReached: $endOfPaginationReached")
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
//        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
//        return if (System.currentTimeMillis() - database.lastUpdated() >= cacheTimeout)
//        {
//            // Cached data is up-to-date, so there is no need to re-fetch
//            // from the network.
//            InitializeAction.SKIP_INITIAL_REFRESH
//        } else {
//            // Need to refresh cached data from network; returning
//            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
//            // APPEND and PREPEND from running until REFRESH succeeds.
//            InitializeAction.LAUNCH_INITIAL_REFRESH
//        }

        // Require that remote REFRESH is launched on initial load and succeeds before launching
        // remote PREPEND / APPEND.
        Log.e("Sinva", "initialize()..")
        return InitializeAction.LAUNCH_INITIAL_REFRESH

    }

}