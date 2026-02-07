package com.example.librarytrackerapp.domain.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val image: String
)