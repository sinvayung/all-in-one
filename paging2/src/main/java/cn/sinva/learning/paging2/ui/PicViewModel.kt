package cn.sinva.learning.paging2.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cn.sinva.learning.paging2.repository.PicRepository
import cn.sinva.learning.paging2.vo.Pic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

class PicViewModel(
    private val repository: PicRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val KEY_QUERY_TEXT = "query_text"
        const val DEFAULT_QUERY_TEXT = "sinva"
    }

    init {
        if (!savedStateHandle.contains(KEY_QUERY_TEXT)) {
            savedStateHandle.set(KEY_QUERY_TEXT, DEFAULT_QUERY_TEXT)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)  // sinva ???
    var pics = savedStateHandle.getLiveData<String>(KEY_QUERY_TEXT)
        .asFlow()
        .flatMapLatest { repository.picsOfQueryText("sinva", 30) }
        .cachedIn(viewModelScope)


    fun getPicsx(): Flow<PagingData<Pic>> {
        return repository.picsOfQueryText("sinva", 30)
    }


}