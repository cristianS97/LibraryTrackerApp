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
        viewModelScope.launch {
            val token = doLoginUseCase(_username.value ?: "", _password.value ?: "")
            if(token?.accessToken?.isNotEmpty() == true) {
                Log.i("Mi token", token.accessToken)
            } else {
                Log.i("Mi token", "No se ha realizado el login")
            }
        }
    }
}