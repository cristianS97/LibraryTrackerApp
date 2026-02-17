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
fun CreateEditBookScreenDescriptionInput(description: String, descriptionChange: (String) -> Unit) {
    Text(
        "Description",
        modifier = Modifier.padding(vertical = 16.dp),
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    OutlinedTextField(
        value = description,
        onValueChange = { descriptionChange(it) },
        placeholder = {
            Text("Write a brief synopsis or your thoughts on the book...")
        },
        modifier = Modifier.fillMaxWidth(),
        minLines = 3,
        maxLines = 10,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    )
}