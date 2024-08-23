package com.clutchit.loginapp77.data.repository.login

import com.clutchit.loginapp77.data.models.User
import com.clutchit.loginapp77.data.models.UserToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepositoryImpl(
    private val loginRepository: LoginRepository
): LoginRepository {
    override suspend fun getUsers(): Response<List<String>> {
        return withContext(Dispatchers.IO) {
            loginRepository.getUsers()
        }
    }

    override suspend fun authenticateUser(user: User): Response<UserToken> {
        return withContext(Dispatchers.IO) {
            loginRepository.authenticateUser(user = user)
        }
    }
}