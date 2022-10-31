package cn.sinva.learning.paging2.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import cn.sinva.learning.paging2.GlideRequests
import cn.sinva.learning.paging2.vo.Pic

class PicAdapter(private val glide: GlideRequests) : PagingDataAdapter<Pic, PicViewHolder>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
        return PicViewHolder.create(parent, glide)
    }

    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val POST_COMPARATOR = object: DiffUtil.ItemCallback<Pic>() {
            override fun areItemsTheSame(oldItem: Pic, newItem: Pic): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Pic, newItem: Pic): Boolean =
                oldItem == newItem

        }
    }
}
