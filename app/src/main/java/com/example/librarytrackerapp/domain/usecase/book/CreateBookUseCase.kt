package com.example.librarytrackerapp.domain.usecase.book

import android.net.Uri
import com.example.librarytrackerapp.domain.repository.BookTrackerRepository
import javax.inject.Inject

class CreateBookUseCase @Inject constructor(private val bookTrackerRepository: BookTrackerRepository) {
    suspend operator fun invoke(
        token: String,
        title: String,
        author: String,
        description: String?,
        imageUri: Uri
    ): Result<Unit> {
        return try {
            bookTrackerRepository.createBook(
                token = token,
                title = title,
                author = author,
                description = description,
                imageUri = imageUri
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}