package cn.sinva.learning.paging2.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pics")
data class Pic (
    val id: Int,

    @PrimaryKey
    val t: String,

    val turl: String,

    val murl: String,

    val purl: String
)