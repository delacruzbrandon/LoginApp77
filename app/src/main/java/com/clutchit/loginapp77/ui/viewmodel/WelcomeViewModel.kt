package com.clutchit.loginapp77.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.clutchit.loginapp77.data.repository.welcome.WelcomeRepository
import com.clutchit.loginapp77.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WelcomeViewModel(
    private val welcomeRepository: WelcomeRepository
): ViewModel() {
    private val TAG = "WelcomeViewModel"

    private val _usernameState = MutableStateFlow<RequestState<String?>>(RequestState.Idle)
    val usernameState: StateFlow<RequestState<String?>> = _usernameState

    suspend fun getUsername(token: String){
        try {
            val userToken = "Bearer $token"
            val response = welcomeRepository.verifyUser(userToken)

            Log.d(TAG, "getUsername: $userToken")
            if (response.isSuccessful) {
                _usernameState.value = RequestState.Success(response.body()?.get(0)?.username)
                Log.d(TAG, "response: ${response.body()?.get(0)?.username}")
            } else {
                _usernameState.value = RequestState.Error(Throwable(response.message()))
                Log.d(TAG, "response: ${response.message()}")
            }

        } catch (e: Error) {
            _usernameState.value = RequestState.Error(Throwable(e))
            Log.d(TAG, "getUsername: $e")

        }
    }
}