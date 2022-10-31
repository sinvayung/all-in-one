package cn.sinva.learning.paging4.adapters

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.sinva.learning.paging4.GlideRequests
import cn.sinva.learning.paging4.data.Beauty

class BeautyAdapter(
    private val glide: GlideRequests
) : PagingDataAdapter<Beauty, BeautyViewHolder>(BeautyComparator) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BeautyViewHolder {
        return BeautyViewHolder.create(parent, glide)
    }

    override fun onBindViewHolder(holder: BeautyViewHolder, position: Int) {
        val item = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        holder.bind(item)
    }

    companion object {
        object BeautyComparator : DiffUtil.ItemCallback<Beauty>() {
            override fun areItemsTheSame(oldItem: Beauty, newItem: Beauty): Boolean {
                // Id is unique.
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Beauty, newItem: Beauty): Boolean {
                return oldItem == newItem
            }
        }
    }
}