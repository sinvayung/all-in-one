package cn.sinva.learning.paging3.api
import cn.sinva.learning.paging3.data.Beauty
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BeautyBackendService {

    @GET("/beauties")
    suspend fun searchBeauties(
        @Query("query") query: String? = null,
        @Query("nextPageNumber") nextPageNumber: Int? = null
    ): ListResponse

    data class ListResponse (
        val beauties: List<Beauty>,
        val nextPageNumber: Int
    )

    companion object{
        private const val BASE_URL = "http://192.168.31.193:8000"

        fun create(): BeautyBackendService {
            val logger = HttpLoggingInterceptor()

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