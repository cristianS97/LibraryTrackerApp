package com.example.librarytrackerapp.ui.screens.bookdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.domain.usecase.book.GetBookByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailScreenViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase
) : ViewModel() {
    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    fun obtenerLibro(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val response = getBookByIdUseCase(id)
            response.onSuccess { bookResult ->
                _book.value = bookResult
            }.onFailure { exception ->
                _errorMessage.value = exception.message ?: "Error al obtener el libro"
            }
            _isLoading.value = false
        }
    }
}