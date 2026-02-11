package com.example.librarytrackerapp.ui.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarytrackerapp.domain.usecase.auth.DoLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val doLoginUseCase: DoLoginUseCase
) : ViewModel() {
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin : LiveData<Boolean> = _isLogin

    private val _username = MutableLiveData<String>()
    val username : LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _showPassword = MutableLiveData<Boolean>()
    val showPassword : LiveData<Boolean> = _showPassword

    private val _loginSuccess = MutableLiveData<Boolean>(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        _isLogin.value = true
    }

    fun changeSection() {
        _isLogin.value = !_isLogin.value!!
        _username.value = ""
        _password.value = ""
    }

    fun changeUsername(username: String) {
        _username.value = username
    }

    fun changePassword(password: String) {
        _password.value = password
    }

    fun changePasswordVisibility() {
        _showPassword.value = !(_showPassword.value ?: false)
    }

    fun doAction() {
        if(_isLogin.value ?: false) {
            doLogin()
        }
    }

    fun doLogin() {
        val currentUsername = _username.value ?: ""
        val currentPassword = _password.value ?: ""

        if (currentUsername.isEmpty() || currentPassword.isEmpty()) {
            _errorMessage.value = "Por favor, completa todos los campos"
        } else {
            viewModelScope.launch {
                try {
                    val token = doLoginUseCase(_username.value ?: "", _password.value ?: "")
                    if (token?.accessToken?.isNotEmpty() == true) {
                        Log.i("Contraseña","Correcta")
                        _loginSuccess.value = true
                    } else {
                        Log.i("Contraseña","Incorrecta")
                        _errorMessage.value = "Credenciales incorrectas"
                    }
                } catch (e: Exception) {
                    _errorMessage.value = "Error de conexión: ${e.message}"
                }
            }
        }
    }

    fun onNavigationDone() {
        _loginSuccess.value = false
    }

    fun resetErrorMessage() {
        _errorMessage.value = null
    }
}