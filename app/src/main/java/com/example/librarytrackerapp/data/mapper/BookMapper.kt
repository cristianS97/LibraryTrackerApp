package com.example.librarytrackerapp.data.mapper

import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.data.network.book.BookDto
import com.example.librarytrackerapp.util.NetworkConstants

fun BookDto.toDomain() : Book {
    return Book(
        id = this.id,
        title = this.title,
        author = this.author,
        description = this.description,
        image = "${NetworkConstants.BASE_URL}${this.image}"
    )
}

fun Book.toDto(): BookDto {
    return BookDto(
        id = this.id,
        title = this.title,
        author = this.author,
        description = this.description,
        image = this.image.replace(NetworkConstants.BASE_URL, "")
    )
}