package cn.sinva.learn.dagger2

import dagger.Component
import javax.inject.Singleton

// @Component
@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

}