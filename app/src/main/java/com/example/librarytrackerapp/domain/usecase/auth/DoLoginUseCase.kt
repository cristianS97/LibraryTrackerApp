package com.example.librarytrackerapp.domain.usecase.auth

import com.example.librarytrackerapp.domain.model.Login
import com.example.librarytrackerapp.domain.repository.AuthRepository
import javax.inject.Inject

class DoLoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<Login> {
        return try {
            val loginData = authRepository.doLogin(username, password)
            Result.success<Login>(loginData)
        } catch (e: Exception) {
            Result.failure<Login>(e)
        }
    }
}