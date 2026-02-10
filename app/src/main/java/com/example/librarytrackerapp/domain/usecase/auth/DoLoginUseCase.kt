package com.example.librarytrackerapp.domain.usecase.auth

import com.example.librarytrackerapp.domain.model.Login
import com.example.librarytrackerapp.domain.repository.AuthRepository
import javax.inject.Inject

class DoLoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String) : Login? {
        return authRepository.doLogin(username, password)
    }
}