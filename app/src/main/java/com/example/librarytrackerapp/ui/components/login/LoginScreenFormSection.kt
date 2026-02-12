package com.example.librarytrackerapp.ui.components.login

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.librarytrackerapp.ui.screens.login.LoginViewModel

@Composable
fun LoginScreenFormSection(loginViewModel: LoginViewModel) {
    val isLogin by loginViewModel.isLogin.observeAsState(initial = true)
    val password by loginViewModel.password.observeAsState(initial = "")
    val password2 by loginViewModel.password2.observeAsState(initial = "")
    val showPassword by loginViewModel.showPassword.observeAsState(initial = false)
    val showPassword2 by loginViewModel.showPassword2.observeAsState(initial = false)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(Color(0xFF232F48), RoundedCornerShape(12.dp))
            .padding(4.dp)
    ) {
        LoginScreenSegmentItem(
            label = "Sign In",
            isSelected = isLogin,
            modifier = Modifier.weight(1f),
            onClick = {
                loginViewModel.changeSection()
            }
        )
        LoginScreenSegmentItem(
            label = "Create Account",
            isSelected = !isLogin,
            modifier = Modifier.weight(1f),
            onClick = {
                loginViewModel.changeSection()
            }
        )
    }
    AnimatedContent(
        targetState = isLogin,
        label = "form_transition"
    ) { targetIsLogin ->
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            if (targetIsLogin) {
                LoginScreenLoginUsername(loginViewModel = loginViewModel)
                LoginScreenLoginPassword(
                    password = password,
                    showPassword = showPassword,
                    isLogin = isLogin,
                    changePassword = { loginViewModel.changePassword(it) },
                    changePasswordVisibility = { loginViewModel.changePasswordVisibility() }
                )
            } else {
                LoginScreenLoginUsername(loginViewModel = loginViewModel)
                LoginScreenLoginPassword(
                    password = password,
                    showPassword = showPassword,
                    isLogin = isLogin,
                    changePassword = { loginViewModel.changePassword(it) },
                    changePasswordVisibility = { loginViewModel.changePasswordVisibility() }
                )
                LoginScreenLoginPassword(
                    password = password2,
                    showPassword = showPassword2,
                    isLogin = isLogin,
                    changePassword = { loginViewModel.changePassword2(it) },
                    changePasswordVisibility = { loginViewModel.changePassword2Visibility() }
                )
            }

            Spacer(Modifier.height(8.dp))
            LoginScreenButtonLogin(
                loginViewModel = loginViewModel,
                text = if (targetIsLogin) "Sign In" else "Create Account"
            )
        }
    }
}