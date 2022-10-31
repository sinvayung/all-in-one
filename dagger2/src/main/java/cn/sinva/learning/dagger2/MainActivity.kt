package cn.sinva.learn.dagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.sinva.learning.dagger2.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as MyApplication).appComponent.inject(this)
        Log.d("Sinva", "loginViewModel = $loginViewModel")

        setContentView(R.layout.activity_main)
    }
}