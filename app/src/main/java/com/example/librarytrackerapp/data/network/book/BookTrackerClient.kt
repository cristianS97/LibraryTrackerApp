package com.example.librarytrackerapp.data.network.book

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookTrackerClient {
    @GET("book/")
    suspend fun getBooks(): Response<List<BookDto>>

    @GET("book/{id}/")
    suspend fun getBook(@Path("id") id: Int): Response<BookDto>
}