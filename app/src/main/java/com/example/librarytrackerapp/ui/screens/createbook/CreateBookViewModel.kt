package com.example.librarytrackerapp.ui.screens.createbook

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarytrackerapp.domain.usecase.book.CreateBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateBookViewModel @Inject constructor(
    private val createBookUseCase: CreateBookUseCase
): ViewModel() {
    private val _bookName = MutableLiveData<String>()
    val bookName : LiveData<String> = _bookName

    private val _author = MutableLiveData<String>()
    val author : LiveData<String> = _author

    private val _description = MutableLiveData<String>()
    val description : LiveData<String> = _description

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri : LiveData<Uri?> = _imageUri

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
}