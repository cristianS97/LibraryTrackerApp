package com.example.librarytrackerapp.domain.repository

import com.example.librarytrackerapp.domain.model.Login

interface AuthRepository {
    suspend fun doLogin(username: String, password: String) : Login?
    fun isUserLoggedIn() : Boolean
    fun closeSession()
}