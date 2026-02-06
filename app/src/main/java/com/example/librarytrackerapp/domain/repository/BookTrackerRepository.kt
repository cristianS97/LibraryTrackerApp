package com.example.librarytrackerapp.domain.repository

import com.example.librarytrackerapp.domain.model.Book

interface BookTrackerRepository {
    suspend fun getBooks(): List<Book>
}