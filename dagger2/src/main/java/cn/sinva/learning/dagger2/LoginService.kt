package cn.sinva.learn.dagger2

interface LoginService {

    fun query(username: String, password: String): String

}