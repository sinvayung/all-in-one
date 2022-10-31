package cn.sinva.learning.paging4.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cn.sinva.learning.paging4.data.Beauty

@Dao
interface BeautyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beauties: List<Beauty>)

//    @Query("SELECT * FROM beauties WHERE beauty_desc LIKE :query")
//    fun pagingSource(query: String): PagingSource<Int, Beauty>

    @Query("SELECT * FROM beauties")
    fun pagingSource(): PagingSource<Int, Beauty>

    @Query("DELETE FROM beauties")
    suspend fun clearAll()

}