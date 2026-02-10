package com.example.librarytrackerapp.data.network.book

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("author")
    val author: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("image")
    val image: String
)