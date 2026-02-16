package com.example.librarytrackerapp.data.network.book

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface BookTrackerClient {
    @GET("book/")
    suspend fun getBooks(): Response<List<BookDto>>

    @GET("book/{id}/")
    suspend fun getBook(@Path("id") id: Int): Response<BookDto>

    @Multipart
    @POST("book/")
    suspend fun createBook(
        @Header("Authorization") token: String,
        @Part("title") title: RequestBody,
        @Part("author") author: RequestBody,
        @Part("description") description: RequestBody?,
        @Part file: MultipartBody.Part
    ): Response<Unit>
}