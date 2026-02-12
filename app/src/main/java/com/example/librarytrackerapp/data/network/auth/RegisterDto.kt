package com.example.librarytrackerapp.data.network.auth

import com.google.gson.annotations.SerializedName

data class RegisterDto(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("role")
    val role: String
)
