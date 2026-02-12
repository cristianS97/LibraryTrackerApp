package com.example.librarytrackerapp.domain.model

data class Register(
    val username: String,
    val password: String,
    val role: String = "user"
)
