package cn.sinva.remember.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.sinva.remember.R

class LoginViewModel: ViewModel() {


    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // TODO
        if (username == "sinva" && password == "123456") {
            _loginResult.value = LoginResult(success = true)
        } else {
            _loginResult.value = LoginResult(error = R.string.msg_login_failed)
        }
    }

    fun  loginDataChanged(username: String, password: String) {
        if (!isUsernameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.msg_username_invalid)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.msg_password_invalid)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUsernameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean = password.length > 5




}