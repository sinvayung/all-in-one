package cn.sinva.learning.paging3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import cn.sinva.learning.paging3.api.BeautyBackendService
import cn.sinva.learning.paging3.data.BeautyPagingSource

class BeautyViewModel(
    private val backend: BeautyBackendService = BeautyBackendService.create(),
    private val query: String = ""
) : ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        BeautyPagingSource(backend, query)
    }.flow
        .cachedIn(viewModelScope)

}