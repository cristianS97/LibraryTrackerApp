package com.example.librarytrackerapp.data.mapper

import com.example.librarytrackerapp.data.network.auth.RegisterDto
import com.example.librarytrackerapp.domain.model.Register

fun RegisterDto.toDomain(): Register {
    return Register(
        username = this.username,
        password = this.password,
        role = this.role
    )
}

fun Register.toDto(): RegisterDto {
    return RegisterDto(
        username = this.username,
        password = this.password,
        role = this.role
    )
}