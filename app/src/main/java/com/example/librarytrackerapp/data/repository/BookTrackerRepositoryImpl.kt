package com.example.librarytrackerapp.data.repository

import com.example.librarytrackerapp.data.mapper.toDomain
import com.example.librarytrackerapp.data.mapper.toDto
import com.example.librarytrackerapp.data.network.book.BookTrackerService
import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.domain.repository.BookTrackerRepository
import javax.inject.Inject

class BookTrackerRepositoryImpl @Inject constructor(
    private val bookTrackerService: BookTrackerService
) : BookTrackerRepository {
    override suspend fun getBooks(): List<Book> {
        return bookTrackerService.getBooks().map { it.toDomain() }
    }

    override suspend fun getBook(id: Int): Book {
        val response = bookTrackerService.getBook(id)

        return when(response.code()) {
            200 -> {
                val dto = response.body() ?: throw Exception("Respuesta vacía")
                dto.toDomain()
            }
            404 -> throw Exception("El libro no ha sido encontrado")
            else -> throw Exception("Error inesperado: ${response.code()}")
        }
    }
}