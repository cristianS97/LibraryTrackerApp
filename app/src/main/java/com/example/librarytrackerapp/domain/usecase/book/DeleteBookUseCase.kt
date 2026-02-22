package com.example.librarytrackerapp.domain.usecase.book

import com.example.librarytrackerapp.domain.repository.BookTrackerRepository
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(private val bookTrackerRepository: BookTrackerRepository) {
    suspend operator fun invoke(token: String, id: Int): Result<Unit> {
        return try {
            bookTrackerRepository.deleteBook(token, id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}