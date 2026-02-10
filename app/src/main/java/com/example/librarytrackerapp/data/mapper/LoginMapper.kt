package com.example.librarytrackerapp.data.mapper

import com.example.librarytrackerapp.data.network.auth.LoginDto
import com.example.librarytrackerapp.domain.model.Login

fun LoginDto.toDomain() : Login {
    return Login(
        tokenType = this.tokenType,
        accessToken = this.accessToken
    )
}

fun Login.toDto() : LoginDto {
    return LoginDto(
        tokenType = this.tokenType,
        accessToken = this.accessToken
    )
}