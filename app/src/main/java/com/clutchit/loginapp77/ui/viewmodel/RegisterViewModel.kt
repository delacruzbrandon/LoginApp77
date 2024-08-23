package com.clutchit.loginapp77.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.clutchit.loginapp77.data.models.User
import com.clutchit.loginapp77.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel: ViewModel() {
    private val _userRegisterState = MutableStateFlow<RequestState<User>>(RequestState.Idle)
    val userRegisterState: StateFlow<RequestState<User>> = _userRegisterState

    private val _goLogin = MutableStateFlow(false)
    val goLogin: StateFlow<Boolean> = _goLogin

    private val _usernameState = MutableStateFlow("")
    val username: StateFlow<String> = _usernameState

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun setUsername(email: String) {
        _usernameState.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun goLogin() {
        _goLogin.value = true
    }

    suspend fun isInputValid(user: User) {
        if (user.username.isEmpty() || user.password.isEmpty()) {
            registerError()
        } else {
            registerUser(user)
        }
    }

    fun loadNewUser() {
        _userRegisterState.value = RequestState.Loading
    }

    private fun registerError() {
        _userRegisterState.value = RequestState.Error(Throwable("Registration Error!"))
    }

    private suspend fun registerUser(user: User) {
        _userRegisterState.value = RequestState.Success(user)
    }
}