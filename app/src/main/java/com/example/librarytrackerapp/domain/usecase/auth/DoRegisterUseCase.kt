package com.example.librarytrackerapp.domain.usecase.auth

import com.example.librarytrackerapp.domain.model.Register
import com.example.librarytrackerapp.domain.repository.AuthRepository
import javax.inject.Inject

class DoRegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(register: Register): Result<Unit> {
        return try {
            val success = authRepository.doRegister(register)
            if (success) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error desconocido al registrar"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}