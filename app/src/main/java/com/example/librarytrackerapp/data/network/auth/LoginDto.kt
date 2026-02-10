package com.example.librarytrackerapp.data.network.auth

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("token_type")
    val tokenType: String
)
