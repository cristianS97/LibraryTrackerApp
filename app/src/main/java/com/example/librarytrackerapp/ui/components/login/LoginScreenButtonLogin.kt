package com.example.librarytrackerapp.ui.components.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarytrackerapp.ui.screens.login.LoginViewModel

@Composable
fun LoginScreenButtonLogin(loginViewModel: LoginViewModel, text: String) {
    val isLogin by loginViewModel.isLogin.observeAsState()
    val username by loginViewModel.username.observeAsState("")
    val password by loginViewModel.password.observeAsState("")
    var isButtonEnabled by remember { mutableStateOf(false) }

    if(isLogin ?: false) {
        isButtonEnabled = username.isNotBlank() && password.isNotBlank()
    }

    Button(
        onClick = { loginViewModel.doAction() },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp)),
        enabled = isButtonEnabled
    ) {
        Text(text, color = MaterialTheme.colorScheme.onPrimary, fontSize = 16.sp)
    }
}