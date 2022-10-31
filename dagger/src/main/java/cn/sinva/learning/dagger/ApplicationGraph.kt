package cn.sinva.learn.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationGraph {

    fun repository(): UserRepository

}