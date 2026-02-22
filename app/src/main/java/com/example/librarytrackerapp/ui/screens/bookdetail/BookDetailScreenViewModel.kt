package com.example.librarytrackerapp.ui.screens.bookdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.domain.repository.AuthRepository
import com.example.librarytrackerapp.domain.usecase.book.DeleteBookUseCase
import com.example.librarytrackerapp.domain.usecase.book.GetBookByIdUseCase
import com.example.librarytrackerapp.ui.components.bookdetail.clases.BookStatus
import com.example.librarytrackerapp.util.JwtUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailScreenViewModel @Inject constructor(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn : LiveData<Boolean> = _isLoggedIn

    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _bookStatus = MutableLiveData<BookStatus>(BookStatus.READING)
    val bookStatus: LiveData<BookStatus> = _bookStatus

    private val _userRating = MutableLiveData<Int>(0)
    val userRating: LiveData<Int> = _userRating

    private val _isAdmin = MutableLiveData<Boolean>()
    val isAdmin: LiveData<Boolean> = _isAdmin

    private val _deleteSuccess = MutableLiveData<Boolean>()
    val deleteSuccess: LiveData<Boolean> = _deleteSuccess

    private val _isMenuExpanded = MutableLiveData<Boolean>()
    val isMenuExpanded: LiveData<Boolean> = _isMenuExpanded

    private val _showDeleteDialog = MutableLiveData<Boolean>()
    val showDeleteDialog: LiveData<Boolean> = _showDeleteDialog

    init {
        viewModelScope.launch {
            checkAuthStatus()
        }
    }

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

    fun eliminarLibro(id: Int) {
        viewModelScope.launch {
            val response = deleteBookUseCase(authRepository.getToken() ?: "", id)
            response.onSuccess {
                _deleteSuccess.value = true
            }.onFailure {  }
        }
    }

    fun updateStatus(newStatus: BookStatus) {
        _bookStatus.value = newStatus
    }

    fun onRatingChanged(newRating: Int) {
        _userRating.value = newRating
    }

    fun checkAuthStatus() {
        _isLoggedIn.value = authRepository.isUserLoggedIn()
        if(_isLoggedIn.value == true) {
            val token = authRepository.getToken()
            val role = JwtUtils.getRoleFromToken(token)
            _isAdmin.value = (role == "admin")
        } else {
            _isAdmin.value = false
        }
    }

    fun openMenu() {
        _isMenuExpanded.value = true
    }

    fun closeMenu() {
        _isMenuExpanded.value = false
    }

    fun openDialog() {
        _showDeleteDialog.value = true
    }

    fun closeDialog() {
        _showDeleteDialog.value = false
    }
}