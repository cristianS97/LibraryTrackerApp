package com.example.librarytrackerapp.domain.usecase.book

import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.domain.repository.BookTrackerRepository
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(private val bookTrackerRepository: BookTrackerRepository) {
    suspend operator fun invoke(id: Int): Result<Book> {
        return try {
            val book = bookTrackerRepository.getBook(id)
            Result.success(book)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}