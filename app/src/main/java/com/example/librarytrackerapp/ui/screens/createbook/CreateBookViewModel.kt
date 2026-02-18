package com.example.librarytrackerapp.ui.screens.createbook

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarytrackerapp.domain.repository.AuthRepository
import com.example.librarytrackerapp.domain.usecase.book.CreateBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateBookViewModel @Inject constructor(
    private val createBookUseCase: CreateBookUseCase,
    private val authRepository: AuthRepository
): ViewModel() {
    private val _bookName = MutableLiveData<String>()
    val bookName : LiveData<String> = _bookName

    private val _author = MutableLiveData<String>()
    val author : LiveData<String> = _author

    private val _description = MutableLiveData<String>()
    val description : LiveData<String> = _description

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri : LiveData<Uri?> = _imageUri

    private val _isSuccess = MutableLiveData<Boolean>(false)
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun bookNameChange(bookName: String) {
        _bookName.value = bookName
    }

    fun authorChange(author: String) {
        _author.value = author
    }

    fun descriptionChange(description: String) {
        _description.value = description
    }

    fun imageUriChange(image: Uri?) {
        _imageUri.value = image
    }

    fun createBook() {
        val token = authRepository.getToken()
        viewModelScope.launch {
            try {
                createBookUseCase(
                    token = token!!,
                    title = _bookName.value!!,
                    author = _author.value!!,
                    description = _description.value,
                    imageUri = _imageUri.value!!
                )
                _isSuccess.value = true
            } catch (e: Exception) {
                Log.i("Error creación", "${e.message}")
            }
        }
    }

    fun resetSuccess() {
        _isSuccess.value = false
    }
}