package com.example.librarytrackerapp.data.mapper

import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.data.network.model.BookDto

const val BASE_URL = "http://10.0.2.2:8000"

fun BookDto.toDomain() : Book {
    return Book(
        id = this.id,
        title = this.title,
        author = this.author,
        description = this.description,
        image = "$BASE_URL${this.image}"
    )
}

fun Book.toDto(): BookDto {
    return BookDto(
        id = this.id,
        title = this.title,
        author = this.author,
        description = this.description,
        image = this.image.replace(BASE_URL, "")
    )
}