package com.clutchit.loginapp77.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.clutchit.loginapp77.data.models.User
import com.clutchit.loginapp77.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel: ViewModel() {
    private val _userLoginState = MutableStateFlow<RequestState<User>>(RequestState.Idle)
    val userLoginState: StateFlow<RequestState<User>> = _userLoginState

    private val _goRegister = MutableStateFlow(false)
    val goRegister: StateFlow<Boolean> = _goRegister

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun setUsername(email: String) {
        _username.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun goRegister() {
        _goRegister.value = true
    }

    suspend fun isInputValid(user: User) {
        if (user.username.isEmpty() || user.password.isEmpty()) {
            loginError()
        } else {
            loginUser(user)
        }
    }

    fun loadUser() {
        _userLoginState.value = RequestState.Loading
    }

    private fun loginError() {
        _userLoginState.value = RequestState.Error(Throwable("Login Error!"))
    }

    private suspend fun loginUser(user: User) {
        _userLoginState.value = RequestState.Success(user)
    }
}