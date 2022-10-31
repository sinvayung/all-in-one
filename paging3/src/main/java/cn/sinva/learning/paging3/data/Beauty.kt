package cn.sinva.learning.paging3.data

import com.google.gson.annotations.SerializedName

data class Beauty (

    val id: Int,

    @SerializedName("t")
    val desc: String,

    @SerializedName("turl")
    val imageUrl: String,

    @SerializedName("murl")
    val largeImageUrl: String,

    @SerializedName("purl")
    val pageUrl: String

)