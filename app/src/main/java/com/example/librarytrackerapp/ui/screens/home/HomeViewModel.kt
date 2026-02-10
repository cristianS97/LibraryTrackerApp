package com.example.librarytrackerapp.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.domain.usecase.book.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getBooksUseCase: GetBooksUseCase
) : ViewModel() {
    private val _books = MutableLiveData<List<Book>>()
    val books : LiveData<List<Book>> = _books

    init {
        viewModelScope.launch {
            _books.value = getBooksUseCase()
        }
    }
}