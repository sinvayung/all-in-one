package cn.sinva.learning.paging2.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.sinva.learning.paging2.GlideRequests
import cn.sinva.learning.paging2.R
import cn.sinva.learning.paging2.ShowActivity
import cn.sinva.learning.paging2.vo.Pic

class PicViewHolder(itemView: View, private val glide: GlideRequests) : RecyclerView.ViewHolder(itemView) {

    private val rowImgView: ImageView = itemView.findViewById(R.id.row_img)
    private val rowTitleView: TextView = itemView.findViewById(R.id.row_title)

    private var pic: Pic? = null

    init {
        pic?.murl?.let { murl ->
            itemView.setOnClickListener {
                var intent = Intent(it.context, ShowActivity::class.java)
                intent.putExtra("murl", murl)
                it.context.startActivity(intent)
            }
        }
    }

    fun bind(pic: Pic?) {
        this.pic = pic
        rowTitleView.text = pic?.t ?: "loading"
        if (pic?.turl?.startsWith("http") == true) {
            glide.load(pic.turl)
                .centerCrop()
                .placeholder(R.drawable.ic_place_holder)
                .into(rowImgView)
        } else {
            glide.clear(rowImgView)
        }
    }

    companion object {
        fun create(parent: ViewGroup, glide: GlideRequests): PicViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pic_item, parent, false)
            return PicViewHolder(view, glide)
        }
    }

}