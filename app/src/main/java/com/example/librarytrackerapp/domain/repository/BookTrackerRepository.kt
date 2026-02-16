package com.example.librarytrackerapp.domain.repository

import com.example.librarytrackerapp.domain.model.Book

interface BookTrackerRepository {
    suspend fun getBooks(): List<Book>
    suspend fun getBook(id: Int): Book
    suspend fun createBook(
        token: String,
        title: String,
        author: String,
        description: String?,
        imageUri: android.net.Uri
    ): Result<Unit>
    suspend fun updateBook(
        token: String,
        id: Int,
        title: String,
        author: String,
        description: String?,
        imageUri: android.net.Uri?
    ): Result<Book>
}