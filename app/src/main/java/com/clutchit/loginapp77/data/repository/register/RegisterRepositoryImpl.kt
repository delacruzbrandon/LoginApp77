package com.clutchit.loginapp77.data.repository.register

import com.clutchit.loginapp77.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RegisterRepositoryImpl(
    private val registerRepository: RegisterRepository
): RegisterRepository {
    override suspend fun addUser(user: User): Response<User> {
        return withContext(Dispatchers.IO) {
            registerRepository.addUser(user = user)
        }
    }
}