package cn.sinva.learn.navigation2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
//        var navController = findNavController(R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController)
        // java.lang.IllegalStateException: You must call setGraph() before calling getGraph()

        // 定义顶部导航区域
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    // 设置左上角返回键点击返回
    override fun onSupportNavigateUp(): Boolean {
        var navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }


}