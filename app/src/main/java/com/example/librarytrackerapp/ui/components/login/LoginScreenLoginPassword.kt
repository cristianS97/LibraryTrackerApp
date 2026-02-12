package com.example.librarytrackerapp.ui.components.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreenLoginPassword(
    password: String,
    showPassword: Boolean,
    isLogin: Boolean,
    changePassword: (String) -> Unit,
    changePasswordVisibility: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if(isLogin) Arrangement.SpaceBetween else Arrangement.Start
    ) {
        Text("Password", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 16.sp)
        if(isLogin) {
            Text("Forgot?", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
        }
    }
    OutlinedTextField(
        value = password,
        onValueChange = { changePassword(it) },
        leadingIcon = {
            Icon(
                Icons.Default.Lock,
                contentDescription = null
            )
        },
        placeholder = {
            Text("••••••••")
        },
        trailingIcon = {
            Icon(
                Icons.Default.Visibility,
                contentDescription = null,
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = {
                        changePasswordVisibility()
                    }
                )
            )
        },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
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