package cn.sinva.learning.paging2.api

import cn.sinva.learning.paging2.vo.Pic
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicApi {

    @GET("/images/{queryText}")
    suspend fun getPics(
        @Path("queryText") queryText: String,
        @Query("limit") limit: Int,
        @Query("after") after: Int? = null,
        @Query("before") before: Int? = null
    ): Result

    data class Result(
        val data: List<Pic>,
        val after: Int,
        val before: Int
    )

    companion object{
        private const val BASE_URL = "http://192.168.31.193:8000"

        fun create(): PicApi {
            val logger = HttpLoggingInterceptor()

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL.toHttpUrlOrNull())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PicApi::class.java)
        }
    }

}