package cn.sinva.learning.paging2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import cn.sinva.learning.paging2.api.PicApi
import cn.sinva.learning.paging2.databinding.ActivityMainBinding
import cn.sinva.learning.paging2.paging.asMergedLoadStates
import cn.sinva.learning.paging2.repository.InMemoryByPageKeyRepository
import cn.sinva.learning.paging2.repository.PageKeyedPicPagingSource
import cn.sinva.learning.paging2.ui.PicAdapter
import cn.sinva.learning.paging2.ui.PicLoadStateAdapter
import cn.sinva.learning.paging2.ui.PicViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
        private set

    private val model: PicViewModel by viewModels {
        object : AbstractSavedStateViewModelFactory(this, null) {
            override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                val picApi = PicApi.create()
                val repo  = InMemoryByPageKeyRepository(picApi)
                return PicViewModel(repo, handle) as T
            }

        }
    }

    private lateinit var adapter: PicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initSwipeToRefresh()

    }

    private fun initAdapter() {
        val glide = GlideApp.with(this)
        adapter = PicAdapter(glide)
        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PicLoadStateAdapter(adapter),  // sinva ??
            footer = PicLoadStateAdapter(adapter)
        )

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect {
                binding.swipeRefresh.isRefreshing = it.mediator?.refresh is LoadState.Loading
            }
        }

//        lifecycleScope.launchWhenCreated {
//            model.pics.collectLatest {
//                adapter.submitData(it)
//            }
//        }

        lifecycleScope.launchWhenCreated {
            model.getPicsx().collect {
                adapter.submitData(it)
            }
        }


        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow
                .asMergedLoadStates()
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.recyclerView.scrollToPosition(0) }
        }

    }

    private fun initSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh()
        }
    }

}