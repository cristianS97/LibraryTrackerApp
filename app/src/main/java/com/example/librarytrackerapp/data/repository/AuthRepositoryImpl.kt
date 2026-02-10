package com.example.librarytrackerapp.data.repository

import android.content.SharedPreferences
import com.example.librarytrackerapp.data.mapper.toDomain
import com.example.librarytrackerapp.data.network.auth.AuthService
import com.example.librarytrackerapp.domain.model.Login
import com.example.librarytrackerapp.domain.repository.AuthRepository
import com.example.librarytrackerapp.util.NetworkConstants
import javax.inject.Inject
import androidx.core.content.edit

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val prefs: SharedPreferences
) : AuthRepository {
    override suspend fun doLogin(username: String, password: String): Login? {
        val dto = authService.doLogin(username, password)

        return if(dto != null && dto.accessToken.isNotEmpty()) {
            prefs.edit { putString(NetworkConstants.TOKEN_PREF_KEY, dto.accessToken) }
            dto.toDomain()
        } else {
            null
        }
    }
}