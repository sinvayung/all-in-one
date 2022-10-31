package cn.sinva.learn.dagger3

import dagger.Subcomponent
import javax.inject.Singleton

@ActivityScope
@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(mainActivity: MainActivity)
}