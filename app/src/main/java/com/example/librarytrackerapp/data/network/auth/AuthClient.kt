package com.example.librarytrackerapp.data.network.auth

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthClient {
    @FormUrlEncoded
    @POST("users/login")
    suspend fun doLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Response<LoginDto>
}