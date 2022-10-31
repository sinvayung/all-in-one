package cn.sinva.learning.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import cn.sinva.learning.paging3.adapters.BeautyAdapter
import cn.sinva.learning.paging3.api.BeautyBackendService
import cn.sinva.learning.paging3.data.BeautyPagingSource
import cn.sinva.learning.paging3.databinding.ActivityMainBinding
import cn.sinva.learning.paging3.viewmodels.BeautyViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val glide = GlideApp.with(this)

        val backend = BeautyBackendService.create()
        val query = binding.searchBeauty.text.toString()

        val viewModel by viewModels<BeautyViewModel>()

        val pagingAdapter = BeautyAdapter(glide)
        binding.recyclerView.adapter = pagingAdapter

        // Activities can use lifecycleScope directly, but Fragments should instead use
        // viewLifecycleOwner.lifecycleScope.
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }





    }
}