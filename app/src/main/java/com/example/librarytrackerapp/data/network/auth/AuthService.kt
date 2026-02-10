package com.example.librarytrackerapp.data.network.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthService @Inject constructor(private val authClient: AuthClient) {
    suspend fun doLogin(username: String, password: String) : LoginDto? {
        return withContext(Dispatchers.IO) {
            val response = authClient.doLogin(username, password)
            if(response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }
}