package com.clutchit.loginapp77.data.repository.register

import com.clutchit.loginapp77.data.models.User
import com.clutchit.loginapp77.util.Constants
import com.clutchit.loginapp77.util.Constants.PATH_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RegisterRepository {
    @POST(PATH_URL + Constants.REGISTER_ADD)
    suspend fun addUser(@Body user: User): Response<User>
}