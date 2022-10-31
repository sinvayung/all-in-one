package cn.sinva.learning.paging4.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cn.sinva.learning.paging4.data.Beauty

@Database(
    entities = [Beauty::class],
    version = 1,
    exportSchema = false)
abstract class BeautyDatabase : RoomDatabase() {

    abstract fun beautyDao(): BeautyDao

    companion object {
        fun create(context: Context): BeautyDatabase {
            return Room.databaseBuilder(context, BeautyDatabase::class.java, "beauty.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}