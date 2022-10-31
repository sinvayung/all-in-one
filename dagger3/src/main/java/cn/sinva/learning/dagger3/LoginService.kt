package cn.sinva.learn.dagger3

interface LoginService {

    fun query(username: String, password: String): String

}