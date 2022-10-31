package cn.sinva.learn.dagger3

import dagger.Component
import javax.inject.Singleton

// @Component
@Singleton
@Component(modules = [NetworkModule::class, SubcomponentsModule::class])
interface ApplicationComponent {

    fun loginComponent(): LoginComponent.Factory

}