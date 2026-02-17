package com.example.librarytrackerapp.ui.components.createedit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateBookScreenTextInput(label: String, text: String, onTextChange: (String) -> Unit) {
    Text(
        label,
        modifier = if (label == "Author") Modifier.padding(vertical = 16.dp) else Modifier.padding(
            bottom = 16.dp
        ),
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    OutlinedTextField(
        value = text,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    )
}