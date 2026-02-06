package com.example.librarytrackerapp.data.network.client

import com.example.librarytrackerapp.data.network.model.BookDto
import retrofit2.Response
import retrofit2.http.GET

interface BookTrackerClient {
    @GET("book/")
    suspend fun getBooks() : Response<List<BookDto>>
}