package cn.sinva.learning.paging4.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class FooterLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<FooterLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = FooterLoadStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(
        holder: FooterLoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)
}