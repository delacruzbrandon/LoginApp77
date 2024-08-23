package com.clutchit.loginapp77.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val id: String = "",

    @SerializedName("__v")
    val number: Int = 0,

    val username: String,
    val password: String
)