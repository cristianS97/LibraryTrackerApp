package com.example.librarytrackerapp.domain.usecase

import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.domain.repository.BookTrackerRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(private val bookTrackerRepository: BookTrackerRepository) {
    suspend operator fun invoke() : List<Book> {
        return bookTrackerRepository.getBooks()
    }
}