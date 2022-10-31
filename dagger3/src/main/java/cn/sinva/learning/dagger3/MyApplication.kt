package cn.sinva.learn.dagger3

import android.app.Application

class MyApplication: Application() {

    var appComponent = DaggerApplicationComponent.create()

}