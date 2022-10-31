package cn.sinva.learn.dagger2

import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) {

}