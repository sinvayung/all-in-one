package cn.sinva.learn.dagger2

import android.app.Application

class MyApplication: Application() {

    var appComponent = DaggerApplicationComponent.create()

}