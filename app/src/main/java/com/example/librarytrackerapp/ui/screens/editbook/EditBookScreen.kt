package com.example.librarytrackerapp.ui.screens.editbook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.ui.components.common.CustomTextField
import com.example.librarytrackerapp.ui.components.createedit.CreateEditBookBottomBar
import com.example.librarytrackerapp.ui.components.createedit.CreateEditBookImagePicker
import com.example.librarytrackerapp.ui.components.createedit.CreateEditBookTopBar
import androidx.core.net.toUri

@Composable
fun EditBookScreen(
    id: Int,
    navigateToBookDetail: (Int) -> Unit,
    viewModel: EditBookScreenViewModel = hiltViewModel()
) {
    val book by viewModel.book.observeAsState(initial = Book(id = 0, title = "", author = "", image = "", description = ""))
    val isSuccess by viewModel.isSuccess.observeAsState(initial =  false)

    LaunchedEffect(id) {
        viewModel.obtenerLibro(id)
    }

    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            navigateToBookDetail(id)
        }
    }

    Scaffold(
        topBar = { CreateEditBookTopBar(title = "Edit Book", navigateToHome = { navigateToBookDetail(id) }) },
        bottomBar = { CreateEditBookBottomBar(
            isButtonEnabled = true,
            onClickButton = { viewModel.onEditConfirm() }
        ) }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            CreateEditBookImagePicker(
                imageUri = book.image.toUri(),
                onImageChange = { viewModel.onImageUriChange(it) }
            )
            CustomTextField(
                label = "Book Name",
                value = book.title,
                onValueChange = { viewModel.onTitleChange(it) }
            )
            CustomTextField(
                label = "Author",
                value = book.author,
                onValueChange = { viewModel.onAuthorChange(it) }
            )
            CustomTextField(
                label = "Description",
                value = book.description,
                onValueChange = { viewModel.onDescriptionChange(it) },
                placeholder = "Write a brief synopsis or your thoughts on the book...",
                minLines = 3,
                maxLines = 10
            )
        }
    }
}
