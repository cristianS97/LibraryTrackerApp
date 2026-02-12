package com.example.librarytrackerapp.domain.repository

import com.example.librarytrackerapp.domain.model.Login
import com.example.librarytrackerapp.domain.model.Register

interface AuthRepository {
    suspend fun doLogin(username: String, password: String): Login
    fun isUserLoggedIn() : Boolean
    fun closeSession()
    suspend fun doRegister(register: Register): Boolean
}