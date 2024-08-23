package com.clutchit.loginapp77.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clutchit.loginapp77.data.models.User
import com.clutchit.loginapp77.data.repository.login.LoginRepository
import com.clutchit.loginapp77.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _userLoginState = MutableStateFlow<RequestState<String?>>(RequestState.Idle)
    val userLoginState: StateFlow<RequestState<String?>> = _userLoginState

    private val _usernameListState = MutableStateFlow<RequestState<List<String>?>>(RequestState.Idle)
    val usernameListState: StateFlow<RequestState<List<String>?>> = _usernameListState

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
            authenticateUser(user)
        }
    }

    fun loadUser() {
        _userLoginState.value = RequestState.Loading
    }

    private fun loginError() {
        _userLoginState.value = RequestState.Error(Throwable("Login Error!"))
    }

    suspend fun getUsers() {
        viewModelScope.launch {
            _usernameListState.value = RequestState.Loading
            try {
                val response = loginRepository.getUsers()
                if (response.isSuccessful) {
                    val users = response.body().orEmpty().map { it }
                    _usernameListState.value = RequestState.Success(users)
                } else {
                    _usernameListState.value = RequestState.Error(Throwable(response.message()))
                }
            } catch (e: Exception) {
                _usernameListState.value = RequestState.Error(Throwable(e.cause))
            }
        }
    }

    private suspend fun authenticateUser(user: User) {
        viewModelScope.launch {
            try {
                val response = loginRepository.authenticateUser(user)

                if (response.isSuccessful) {
                    _userLoginState.value = RequestState.Success(response.body()?.userToken)
                    response.body()?.userToken
                } else {
                    _userLoginState.value = RequestState.Error(Throwable(response.message()))
                    response.body()?.success
                }
            } catch (e: Error) {
                _userLoginState.value = RequestState.Error(Throwable(e))
            }
        }
    }
}