package com.example.librarytrackerapp.ui.screens.createbook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.librarytrackerapp.ui.components.common.CustomTextField
import com.example.librarytrackerapp.ui.components.createedit.CreateEditBookBottomBar
import com.example.librarytrackerapp.ui.components.createedit.CreateEditBookImagePicker
import com.example.librarytrackerapp.ui.components.createedit.CreateEditBookTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBookScreen(
    navigateToHome: () -> Unit,
    createBookViewModel: CreateBookViewModel = hiltViewModel()
) {
    val bookname by createBookViewModel.bookName.observeAsState(initial = "")
    val author by createBookViewModel.author.observeAsState(initial = "")
    val description by createBookViewModel.description.observeAsState(initial = "")
    val imageUri by createBookViewModel.imageUri.observeAsState(initial = null)

    Scaffold(
        topBar = { CreateEditBookTopBar(title = "Create Book", navigateToHome = navigateToHome) },
        bottomBar = { CreateEditBookBottomBar() }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            CreateEditBookImagePicker(
                imageUri = imageUri,
                onImageChange = { createBookViewModel.imageUriChange(it) }
            )
            CustomTextField(
                label = "Book Name",
                value = bookname,
                onValueChange = { createBookViewModel.bookNameChange(it) }
            )
            CustomTextField(
                label = "Author",
                value = author,
                onValueChange = { createBookViewModel.authorChange(it) }
            )
            CustomTextField(
                label = "Description",
                value = description,
                onValueChange = { createBookViewModel.descriptionChange(it) },
                placeholder = "Write a brief synopsis or your thoughts on the book...",
                minLines = 3,
                maxLines = 10
            )
        }
    }
}
