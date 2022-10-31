package cn.sinva.learn.dagger3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import cn.sinva.learning.dagger3.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var loginComponent: LoginComponent
    @Inject
    lateinit var loginViewModel2: LoginViewModel2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginComponent = (applicationContext as MyApplication).appComponent.loginComponent().create()
        loginComponent.inject(this)
        Log.d("Sinva", "loginViewModel2 = $loginViewModel2")

        setContentView(R.layout.activity_main)
    }
}