package com.clutchit.loginapp77.data.repository.login

import com.clutchit.loginapp77.data.models.User
import com.clutchit.loginapp77.data.models.UserToken
import com.clutchit.loginapp77.util.Constants
import com.clutchit.loginapp77.util.Constants.PATH_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginRepository {


    @GET(PATH_URL + Constants.LOGIN_LIST)
    suspend fun getUsers(): Response<List<String>>

    @POST(PATH_URL + Constants.LOGIN_AUTH)
    suspend fun authenticateUser(@Body user: User): Response<UserToken>

}