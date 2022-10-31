package cn.sinva.learning.paging3

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import cn.sinva.learning.paging3.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val largeImageUrl = intent.getStringExtra("largeImageUrl")

        val glideApp = GlideApp.with(this)
        glideApp.load(largeImageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_place_holder)
            .into(binding.imageView)
    }

}