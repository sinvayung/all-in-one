package cn.sinva.learn.hilt

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit


//@Module
//@InstallIn(ActivityComponent::class)
//abstract class AnalyticsModule {
//
//    @Binds
//    abstract fun bindAnalyticsService(
//        analyticsServiceImpl: AnalyticsServiceImpl
//    ): AnalyticsService
//
//}

@Module
@InstallIn(ActivityComponent::class)
object AnalyticsModule {

    @Provides
    fun provideAnalyticsService(): AnalyticsService {
        return Retrofit.Builder()
            .baseUrl("https://sinva.cn")
            .build()
            .create(AnalyticsService::class.java)
    }

}