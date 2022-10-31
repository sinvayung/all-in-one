package cn.sinva.learning.paging2.repository

import androidx.paging.PagingData
import cn.sinva.learning.paging2.vo.Pic
import kotlinx.coroutines.flow.Flow

interface PicRepository {

    fun picsOfQueryText(queryText: String, pageSize: Int): Flow<PagingData<Pic>>

}