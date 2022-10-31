package cn.sinva.learning.paging3.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.sinva.learning.paging3.GlideRequests
import cn.sinva.learning.paging3.R
import cn.sinva.learning.paging3.ShowActivity
import cn.sinva.learning.paging3.data.Beauty
import cn.sinva.learning.paging3.databinding.BeautyViewItemBinding

class BeautyViewHolder(
    private val binding : BeautyViewItemBinding,
    private val glide: GlideRequests
) : RecyclerView.ViewHolder(binding.root) {

    private var beauty: Beauty? = null

    init {
        binding.root.setOnClickListener { view ->
            beauty?.pageUrl?.let { pageUrl ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
                view.context.startActivity(intent)
            }
        }
        binding.avatarView.setOnClickListener { view ->
            beauty?.largeImageUrl?.let { largeImageUrl ->
                val intent = Intent(view.context, ShowActivity::class.java)
                intent.putExtra("largeImageUrl", largeImageUrl)
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(beauty: Beauty?) {
        this.beauty = beauty
        binding.descView.text = beauty?.desc ?: "loading"
        if (beauty?.imageUrl?.startsWith("http") == true) {
            glide.load(beauty.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_place_holder)
                .into(binding.avatarView)
        } else {
            glide.clear(binding.avatarView)
        }
    }

    companion object {
        fun create(parent: ViewGroup, glide: GlideRequests): BeautyViewHolder {
            // val view = LayoutInflater.from(parent.context).inflate(R.layout.beauty_view_item, parent, false)
            val binding = BeautyViewItemBinding.inflate(LayoutInflater.from(parent.context))
            return BeautyViewHolder(binding, glide)
        }
    }

}