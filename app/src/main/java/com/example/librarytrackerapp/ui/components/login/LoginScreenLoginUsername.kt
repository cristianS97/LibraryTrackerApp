package com.example.librarytrackerapp.ui.components.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarytrackerapp.ui.screens.login.LoginViewModel

@Composable
fun LoginScreenLoginUsername(loginViewModel: LoginViewModel) {
    val username by loginViewModel.username.observeAsState(initial = "")
    Text(
        "Username",
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Left,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 16.sp
    )
    OutlinedTextField(
        value = username,
        onValueChange = { loginViewModel.changeUsername(it) },
        placeholder = {
            Text("myusername")
        },
        leadingIcon = {
            Icon(Icons.Default.Email, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.surface,
            unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(12.dp)
    )
}