package cn.sinva.learn.dagger3

import javax.inject.Inject

@ActivityScope
class LoginViewModel2 @Inject constructor(
    private val userRepository: UserRepository
) {

}