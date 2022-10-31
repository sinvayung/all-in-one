package cn.sinva.learn.hilt

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped  // 默认是没有Scope的，加了Scope，在同一Scope中重用对象
class AnalyticsAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    @ApplicationContext private val appContext: Context,
    private val service: AnalyticsService
) {

    fun test() {
        Log.e("Sinva", "context: $context, appContext: $appContext, service: $service")
        // service: retrofit2.Retrofit$1@f78d04a
    }

}