package com.example.librarytrackerapp.data.mapper

import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.data.network.model.BookDto

fun BookDto.toDomain() : Book {
    return Book(
        id = this.id,
        title = this.title,
        author = this.author,
        description = this.description
    )
}

fun Book.toDto(): BookDto {
    return BookDto(
        id = this.id,
        title = this.title,
        author = this.author,
        description = this.description
    )
}