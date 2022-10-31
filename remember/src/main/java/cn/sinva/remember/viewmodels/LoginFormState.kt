package cn.sinva.remember.viewmodels

data class LoginFormState (
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)