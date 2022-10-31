package cn.sinva.learning.paging4.viewmodels

import android.app.Application
import android.content.Context
import android.graphics.pdf.PdfDocument
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import cn.sinva.learning.paging4.api.BeautyBackendService
import cn.sinva.learning.paging4.dao.BeautyDatabase
import cn.sinva.learning.paging4.data.Beauty
import cn.sinva.learning.paging4.data.BeautyConstants
import cn.sinva.learning.paging4.data.BeautyPagingSource
import cn.sinva.learning.paging4.data.BeautyRemoteMediator

class BeautyViewModel(
    private val backend: BeautyBackendService,
    private val database: BeautyDatabase,
    private val query: String = ""
 ) : ViewModel() {

    val flow1 = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = BeautyConstants.PAGE_SIZE)
    ) {
        BeautyPagingSource(backend, query)
    }.flow
//        .cachedIn(viewModelScope)

    @OptIn(ExperimentalPagingApi::class)
    val flow2 = Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = BeautyRemoteMediator(query, database, backend)
    ) {
        database.beautyDao().pagingSource()
    }.flow
        .cachedIn(viewModelScope)

}