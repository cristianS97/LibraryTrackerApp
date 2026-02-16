package com.example.librarytrackerapp.domain.usecase.book

import android.net.Uri
import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.domain.repository.BookTrackerRepository
import javax.inject.Inject

class UpdateBookUseCase @Inject constructor(private val bookTrackerRepository: BookTrackerRepository) {
    suspend operator fun invoke(
        token: String,
        id: Int,
        title: String,
        author: String,
        description: String?,
        imageUri: Uri?
    ): Result<Book> {
        return try {
            bookTrackerRepository.updateBook(
                token = token,
                id = id,
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