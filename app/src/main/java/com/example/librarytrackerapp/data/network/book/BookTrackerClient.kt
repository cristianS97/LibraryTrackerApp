package com.example.librarytrackerapp.data.network.book

import retrofit2.Response
import retrofit2.http.GET

interface BookTrackerClient {
    @GET("book/")
    suspend fun getBooks() : Response<List<BookDto>>
}