package cn.sinva.learning.paging2.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import cn.sinva.learning.paging2.api.PicApi
import cn.sinva.learning.paging2.vo.Pic
import kotlinx.coroutines.flow.Flow

class InMemoryByPageKeyRepository(
    private val picApi: PicApi
) : PicRepository {

    override fun picsOfQueryText(queryText: String, pageSize: Int): Flow<PagingData<Pic>> = Pager(
        PagingConfig(pageSize)
    ) {
        PageKeyedPicPagingSource(
            picApi = picApi,
            queryText = queryText
        )
    }.flow

}