package cn.sinva.learning.paging4.api
import cn.sinva.learning.paging4.data.Beauty
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BeautyBackendService {

    @GET("/beauties1")
    suspend fun searchBeauties1(
        @Query("query") query: String? = null,
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null
    ): ListResponse1

    data class ListResponse1 (
        val beauties: List<Beauty>
    )

    @GET("/beauties2")
    suspend fun searchBeauties2(
        @Query("query") query: String? = null,
        @Query("fromId") fromId: Int? = null,
        @Query("pageSize") pageSize: Int? = null
    ): ListResponse2

    data class ListResponse2 (
        val beauties: List<Beauty>
    )





    companion object{
        private const val BASE_URL = "http://192.168.31.193:8000"

        fun create(): BeautyBackendService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL.toHttpUrlOrNull())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BeautyBackendService::class.java)
        }
    }

}