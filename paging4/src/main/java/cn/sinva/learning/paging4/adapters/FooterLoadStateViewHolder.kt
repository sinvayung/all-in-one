package cn.sinva.learning.paging4.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import cn.sinva.learning.paging4.R
import cn.sinva.learning.paging4.databinding.FooterLoadStateItemBinding

// Adapter that displays a loading spinner when
// state is LoadState.Loading, and an error message and retry
// button when state is LoadState.Error.
class FooterLoadStateViewHolder(
    private val binding: FooterLoadStateItemBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton
            .also {
                it.setOnClickListener { retry() }
            }
    }

    fun bind(loadState: LoadState) {
        Log.e("Sinva", "loadState: $loadState")
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): FooterLoadStateViewHolder {
            // val view = LayoutInflater.from(parent.context).inflate(R.layout.load_state_item, parent, false)
            val binding = FooterLoadStateItemBinding.inflate(LayoutInflater.from(parent.context))
            return FooterLoadStateViewHolder(binding, retry)
        }
    }

}

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }