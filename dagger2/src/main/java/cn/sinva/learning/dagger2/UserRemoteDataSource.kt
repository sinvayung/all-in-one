package cn.sinva.learn.dagger2

import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val loginRetrofitService: LoginService
) {

}