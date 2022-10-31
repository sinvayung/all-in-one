package cn.sinva.learning.paging

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

class CheeseAdapter: PagingDataAdapter<CheeseListItem, CheeseViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseViewHolder {
        return CheeseViewHolder(parent)
    }

    override fun onBindViewHolder(holder: CheeseViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        var diffCallback = object: DiffUtil.ItemCallback<CheeseListItem>() {

            override fun areItemsTheSame(
                oldItem: CheeseListItem,
                newItem: CheeseListItem
            ): Boolean {
                return if (oldItem is CheeseListItem.Item && newItem is CheeseListItem.Item) {
                    oldItem.cheese.id == newItem.cheese.id
                } else if (oldItem is CheeseListItem.Separator && newItem is CheeseListItem.Separator) {
                    oldItem.name == newItem.name
                } else {
                    oldItem == newItem
                }
            }

            override fun areContentsTheSame(
                oldItem: CheeseListItem,
                newItem: CheeseListItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }



}