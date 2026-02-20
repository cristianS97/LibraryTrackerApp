package com.example.librarytrackerapp.ui.screens.editbook

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.domain.repository.AuthRepository
import com.example.librarytrackerapp.domain.usecase.book.GetBookByIdUseCase
import com.example.librarytrackerapp.domain.usecase.book.UpdateBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditBookScreenViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
    private val authRepository: AuthRepository
): ViewModel() {
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn : LiveData<Boolean> = _isLoggedIn

    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isSuccess = MutableLiveData<Boolean>(false)
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _imageUri = MutableLiveData<Uri?>()

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

    fun onTitleChange(title: String) {
        _book.value = _book.value?.copy(title = title)
    }

    fun onAuthorChange(author: String) {
        _book.value = _book.value?.copy(author = author)
    }

    fun onDescriptionChange(description: String) {
        _book.value = _book.value?.copy(description = description)
    }

    fun onImageUriChange(image: Uri?) {
        _imageUri.value = image
        _book.value = _book.value?.copy(image = image.toString())
    }

    fun onEditConfirm() {
        viewModelScope.launch {
            Log.i("Imagen", _book.value?.image!!)
            val response = updateBookUseCase(
                token = authRepository.getToken()!!,
                id = _book.value?.id!!,
                title = _book.value?.title!!,
                author = _book.value?.author!!,
                description = _book.value?.description!!,
                imageUri = _imageUri.value
            )
            if (response.isFailure) {
                val error = response.exceptionOrNull()
                Log.e("Libro Error", "Mensaje: ${error?.message}")
                // Aquí puedes actualizar tu estado de error para la UI
                _errorMessage.value = error?.message ?: "Error desconocido"
            } else {
                _isSuccess.value = true
                Log.i("Libro Éxito", "Libro actualizado correctamente")
            }
        }
    }
}