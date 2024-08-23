package com.clutchit.loginapp77.data.repository.welcome

import com.clutchit.loginapp77.data.models.User
import com.clutchit.loginapp77.util.Constants
import com.clutchit.loginapp77.util.Constants.PATH_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WelcomeRepository {

    @GET(PATH_URL + Constants.LOGIN_VERIFY)
    suspend fun verifyUser(@Header("Authorization") userToken: String): Response<List<User>>
}