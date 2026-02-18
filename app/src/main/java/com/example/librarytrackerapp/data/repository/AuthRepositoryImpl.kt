package com.example.librarytrackerapp.data.repository

import android.content.SharedPreferences
import com.example.librarytrackerapp.data.mapper.toDomain
import com.example.librarytrackerapp.data.network.auth.AuthService
import com.example.librarytrackerapp.domain.model.Login
import com.example.librarytrackerapp.domain.repository.AuthRepository
import com.example.librarytrackerapp.util.NetworkConstants
import javax.inject.Inject
import androidx.core.content.edit
import com.example.librarytrackerapp.data.mapper.toDto
import com.example.librarytrackerapp.domain.model.Register

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val prefs: SharedPreferences
) : AuthRepository {
    override suspend fun doLogin(username: String, password: String): Login {
        val response = authService.doLogin(username, password)

        return when(response.code()) {
            200 -> {
                val dto = response.body() ?: throw Exception("Respuesta vacía")
                prefs.edit { putString(NetworkConstants.TOKEN_PREF_KEY, dto.accessToken) }
                dto.toDomain()
            }
            400 -> throw Exception("Usuario o contraseña incorrectos")
            422 -> throw Exception("Datos mal formados")
            else -> throw Exception("Error inesperado: ${response.code()}")
        }
    }

    override suspend fun doRegister(register: Register): Boolean {
        val response = authService.doRegister(register.toDto())

        return when(response.code()) {
            201 -> true
            409 -> throw Exception("El nombre de usuario ya existe")
            422 -> throw Exception("Los datos enviados son incorrectos")
            else -> throw Exception("Error inesperado: ${response.code()}")
        }
    }

    override fun isUserLoggedIn(): Boolean {
        return !prefs.getString(NetworkConstants.TOKEN_PREF_KEY, "").isNullOrEmpty()
    }

    override fun closeSession() {
        prefs.edit { remove(NetworkConstants.TOKEN_PREF_KEY) }
    }

    override fun getToken(): String? {
        return prefs.getString(NetworkConstants.TOKEN_PREF_KEY, null)
    }
}