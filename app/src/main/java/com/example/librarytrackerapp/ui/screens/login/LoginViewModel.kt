package com.example.librarytrackerapp.ui.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarytrackerapp.domain.model.Register
import com.example.librarytrackerapp.domain.usecase.auth.DoLoginUseCase
import com.example.librarytrackerapp.domain.usecase.auth.DoRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val doLoginUseCase: DoLoginUseCase,
    private val doRegisterUseCase: DoRegisterUseCase
) : ViewModel() {
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _password2 = MutableLiveData<String>()
    val password2: LiveData<String> = _password2

    private val _showPassword = MutableLiveData<Boolean>()
    val showPassword: LiveData<Boolean> = _showPassword

    private val _showPassword2 = MutableLiveData<Boolean>()
    val showPassword2: LiveData<Boolean> = _showPassword2

    private val _loginSuccess = MutableLiveData<Boolean>(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        _isLogin.value = true
    }

    fun changeSection() {
        _isLogin.value = !_isLogin.value!!
        resetInputs()
    }

    fun changeUsername(username: String) {
        _username.value = username
    }

    fun changePassword(password: String) {
        _password.value = password
    }

    fun changePassword2(password: String) {
        _password2.value = password
    }

    fun changePasswordVisibility() {
        _showPassword.value = !(_showPassword.value ?: false)
    }

    fun changePassword2Visibility() {
        _showPassword2.value = !(_showPassword2.value ?: false)
    }

    fun doAction() {
        if (_isLogin.value ?: false) {
            doLogin()
        } else {
            doRegister()
        }
    }

    fun doLogin() {
        viewModelScope.launch {
            val result = doLoginUseCase(_username.value ?: "", _password.value ?: "")
            result.onSuccess { login ->
                Log.i("Login", "Éxito: ${login.accessToken}")
                _loginSuccess.value = true
            }.onFailure { exception ->
                Log.e("Login", "Error: ${exception.message}")
                _errorMessage.value = exception.message ?: "Error al iniciar sesión"
            }
        }
    }

    fun doRegister() {
        viewModelScope.launch {
            val response = doRegisterUseCase(Register(_username.value ?: "", _password.value ?: ""))
            response.onSuccess {
                _isLogin.value = true
                resetInputs()
            }.onFailure {
                _errorMessage.value = it.message ?: "Error inesperado"
            }
        }
    }

    fun onNavigationDone() {
        _loginSuccess.value = false
    }

    fun resetErrorMessage() {
        _errorMessage.value = null
    }

    fun resetInputs() {
        _username.value = ""
        _password.value = ""
        _password2.value = ""
        _showPassword.value = false
        _showPassword2.value = false
    }
}