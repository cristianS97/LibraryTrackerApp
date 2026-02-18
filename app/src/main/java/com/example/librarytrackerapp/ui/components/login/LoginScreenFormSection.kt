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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarytrackerapp.ui.components.common.CustomTextField
import com.example.librarytrackerapp.ui.screens.login.LoginViewModel

@Composable
fun LoginScreenFormSection(loginViewModel: LoginViewModel) {
    val isLogin by loginViewModel.isLogin.observeAsState(initial = true)
    val password by loginViewModel.password.observeAsState(initial = "")
    val password2 by loginViewModel.password2.observeAsState(initial = "")
    val showPassword by loginViewModel.showPassword.observeAsState(initial = false)
    val showPassword2 by loginViewModel.showPassword2.observeAsState(initial = false)


    val username by loginViewModel.username.observeAsState(initial = "")

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
                CustomTextField(
                    label = "Username",
                    value = username,
                    onValueChange = { loginViewModel.changeUsername(it) },
                    placeholder = "myusername",
                    leadingIcon = Icons.Default.Email,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                CustomTextField(
                    label = "Password",
                    value = password,
                    onValueChange = { loginViewModel.changePassword(it) },
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    headerAction = {
                        if (isLogin) {
                            Text("Forgot?", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = { loginViewModel.changePasswordVisibility() } ) {
                            Icon(if(showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription = null)
                        }
                    }
                )
            } else {
                CustomTextField(
                    label = "Username",
                    value = username,
                    onValueChange = { loginViewModel.changeUsername(it) },
                    placeholder = "myusername",
                    leadingIcon = Icons.Default.Email,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                CustomTextField(
                    label = "Password",
                    value = password,
                    onValueChange = { loginViewModel.changePassword(it) },
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    headerAction = {
                        if (isLogin) {
                            Text("Forgot?", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = { loginViewModel.changePasswordVisibility() } ) {
                            Icon(if(showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription = null)
                        }
                    }
                )
                CustomTextField(
                    label = "Password",
                    value = password2,
                    onValueChange = { loginViewModel.changePassword2(it) },
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = if (showPassword2) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    headerAction = {
                        if (isLogin) {
                            Text("Forgot?", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = { loginViewModel.changePasswordVisibility() } ) {
                            Icon(if(showPassword2) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription = null)
                        }
                    }
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