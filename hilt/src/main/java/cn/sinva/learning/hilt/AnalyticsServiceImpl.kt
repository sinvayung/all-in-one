package cn.sinva.learn.hilt

import android.util.Log
import javax.inject.Inject

class AnalyticsServiceImpl @Inject constructor(

): AnalyticsService {
    override fun analyticsMethods() {
        Log.d("Sinva", "Not yet implemented")
    }
}