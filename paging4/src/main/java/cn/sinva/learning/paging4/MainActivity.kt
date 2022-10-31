package cn.sinva.learning.paging4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import cn.sinva.learning.paging4.adapters.BeautyAdapter
import cn.sinva.learning.paging4.adapters.FooterLoadStateAdapter
import cn.sinva.learning.paging4.api.BeautyBackendService
import cn.sinva.learning.paging4.dao.BeautyDatabase
import cn.sinva.learning.paging4.data.BeautyPagingSource
import cn.sinva.learning.paging4.databinding.ActivityMainBinding
import cn.sinva.learning.paging4.viewmodels.BeautyViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<BeautyViewModel>() {
        object: AbstractSavedStateViewModelFactory(this, null) {
            override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                val backend = BeautyBackendService.create()
                val database = BeautyDatabase.create(this@MainActivity)
                val query = "美图"
                return BeautyViewModel(backend, database, query) as T
            }

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val glide = GlideApp.with(this)

//        val backend = BeautyBackendService.create()
//        val query = binding.searchBeauty.text.toString()


        val pagingAdapter = BeautyAdapter(glide)

        val retry = { pagingAdapter.retry() }
        binding.recyclerView.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
            header = FooterLoadStateAdapter(retry),
            footer = FooterLoadStateAdapter(retry)
        )

        // Activities can use lifecycleScope directly, but Fragments should instead use
        // viewLifecycleOwner.lifecycleScope.
        lifecycleScope.launchWhenCreated {
            viewModel.flow1.collectLatest {
            // viewModel.flow2.collectLatest {
                pagingAdapter.submitData(it)
            }
        }


        lifecycleScope.launchWhenCreated {
            // 收集加载事件
            pagingAdapter.loadStateFlow.collectLatest {
                binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
            }
        }

        // 下拉时，刷新PagingAdapter
        binding.swipeRefresh.setOnRefreshListener {
            pagingAdapter.refresh()
        }

    }

}