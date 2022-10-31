package cn.sinva.learning.paging4.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "beauties")
data class Beauty (

    @PrimaryKey
    val id: Int,

    @SerializedName("t")
    @ColumnInfo(name = "beauty_desc")
    val desc: String,

    @SerializedName("turl")
    val imageUrl: String,

    @SerializedName("murl")
    val largeImageUrl: String,

    @SerializedName("purl")
    val pageUrl: String

)