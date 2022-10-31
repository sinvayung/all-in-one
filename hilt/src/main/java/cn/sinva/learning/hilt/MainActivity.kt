package cn.sinva.learn.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.sinva.learning.hilt.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var analytics: AnalyticsAdapter

    @Inject
    lateinit var analytics2: AnalyticsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Sinva", "analytics: $analytics")
        analytics.test()
        Log.d("Sinva", "analytics2: $analytics2")
    }
}