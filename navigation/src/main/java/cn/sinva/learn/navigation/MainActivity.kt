package cn.sinva.learn.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        var navController = findNavController(R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    // 设置左上角返回键点击返回
    override fun onSupportNavigateUp(): Boolean {
        var navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }


}