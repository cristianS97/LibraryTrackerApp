package com.example.librarytrackerapp.data.network.book

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
}