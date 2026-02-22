package com.example.librarytrackerapp.data.network.book

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class BookTrackerService @Inject constructor(private val bookTrackerClient: BookTrackerClient) {
    suspend fun getBooks(): List<BookDto> {
        return withContext(Dispatchers.IO) {
            val response = bookTrackerClient.getBooks()

            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        }
    }

    suspend fun getBook(id: Int): Response<BookDto> {
        return withContext(Dispatchers.IO) {
            bookTrackerClient.getBook(id)
        }
    }

    suspend fun createBook(
        token: String,
        title: RequestBody,
        author: RequestBody,
        description: RequestBody?,
        file: MultipartBody.Part
    ): Response<Unit> {
        return withContext(Dispatchers.IO) {
            bookTrackerClient.createBook(
                token = "Bearer $token",
                title = title,
                author = author,
                description = description,
                file = file
            )
        }
    }

    suspend fun updateBook(
        token: String,
        id: Int,
        title: RequestBody,
        author: RequestBody,
        description: RequestBody?,
        file: MultipartBody.Part?
    ): Response<BookDto> {
        return withContext(Dispatchers.IO) {
            bookTrackerClient.updateBook(
                token = "Bearer $token",
                id = id,
                title = title,
                author = author,
                description = description,
                file = file
            )
        }
    }

    suspend fun deleteBook(token: String, id: Int): Response<Unit> {
        return withContext(Dispatchers.IO) {
            bookTrackerClient.deleteBook("Bearer $token", id)
        }
    }
}