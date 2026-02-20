package com.example.librarytrackerapp.ui.screens.editbook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.librarytrackerapp.ui.components.common.CustomTextField
import com.example.librarytrackerapp.ui.components.createedit.CreateEditBookBottomBar
import com.example.librarytrackerapp.ui.components.createedit.CreateEditBookImagePicker
import com.example.librarytrackerapp.ui.components.createedit.CreateEditBookTopBar

@Composable
fun EditBookScreen(
    id: Int,
    navigateToHome: () -> Unit
) {
    LaunchedEffect(id) {
        // Obtener información del libro
    }

    Scaffold(
        topBar = { CreateEditBookTopBar(title = "Edit Book", navigateToHome = navigateToHome) },
        bottomBar = { CreateEditBookBottomBar(
            isButtonEnabled = true,
            onClickButton = {}
        ) }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            CreateEditBookImagePicker(
                imageUri = null,
                onImageChange = {}
            )
            CustomTextField(
                label = "Book Name",
                value = "",
                onValueChange = {}
            )
            CustomTextField(
                label = "Author",
                value = "",
                onValueChange = {}
            )
            CustomTextField(
                label = "Description",
                value = "",
                onValueChange = {},
                placeholder = "Write a brief synopsis or your thoughts on the book...",
                minLines = 3,
                maxLines = 10
            )
        }
    }
}
