package cn.sinva.learn.dagger2

import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideLoginRetrofitService(
        okHttpClient: OkHttpClient
    ): LoginService {
        Log.e("Sinva", "okHttpClient = $okHttpClient")
        return Retrofit.Builder()
            .baseUrl("https://sinva.cn")
            .build()
            .create(LoginService::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

}