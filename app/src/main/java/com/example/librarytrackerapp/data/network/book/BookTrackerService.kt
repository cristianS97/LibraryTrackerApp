package com.example.librarytrackerapp.data.network.book

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookTrackerService @Inject constructor(private val bookTrackerClient: BookTrackerClient) {
    suspend fun getBooks() : List<BookDto> {
        return withContext(Dispatchers.IO) {
            val response = bookTrackerClient.getBooks()

            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        }
    }
}