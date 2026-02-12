package com.example.librarytrackerapp.data.network.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AuthService @Inject constructor(private val authClient: AuthClient) {
    suspend fun doLogin(username: String, password: String): Response<LoginDto> {
        return withContext(Dispatchers.IO) {
            authClient.doLogin(username, password)
        }
    }

    suspend fun doRegister(registerDto: RegisterDto): Response<Unit> {
        return withContext(Dispatchers.IO) {
            authClient.doRegister(registerDto)
        }
    }
}