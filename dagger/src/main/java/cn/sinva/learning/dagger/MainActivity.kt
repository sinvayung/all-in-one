package cn.sinva.learn.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.sinva.learning.dagger.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val applicationGraph: ApplicationGraph = DaggerApplicationGraph.create()
        val userRepository1: UserRepository = applicationGraph.repository()
        val userRepository2 = applicationGraph.repository()
        Log.d("Sinva", "userRepository1 = $userRepository1")
        Log.d("Sinva", "userRepository1 = $userRepository2")
    }
}