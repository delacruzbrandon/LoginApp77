package com.clutchit.loginapp77.data.repository.welcome

import com.clutchit.loginapp77.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class WelcomeRepositoryImpl(
    private val registerRepository: WelcomeRepository
): WelcomeRepository {
    override suspend fun verifyUser(userToken: String): Response<List<User>> {
        return withContext(Dispatchers.IO) {
            registerRepository.verifyUser(userToken)
        }
    }
}