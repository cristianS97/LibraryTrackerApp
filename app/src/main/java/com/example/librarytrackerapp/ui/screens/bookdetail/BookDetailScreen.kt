package com.example.librarytrackerapp.ui.screens.bookdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.librarytrackerapp.ui.components.bookdetail.BookDetailBookSection
import com.example.librarytrackerapp.ui.components.common.FAB
import com.example.librarytrackerapp.ui.components.common.TopBarApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    id: Int,
    navigateToHome: () -> Unit,
    navigateToEdit: (id: Int) -> Unit,
    viewModel: BookDetailScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(id) {
        viewModel.obtenerLibro(id)
    }

    val book by viewModel.book.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val isLoggedIn by viewModel.isLoggedIn.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState()
    val randomRating = ((0..50).random() * 0.1).let { "%.2f".format(it).toDouble() }

    Scaffold(
        topBar = {
            TopBarApp("Book Details", navigateToHome = navigateToHome)
        },
        floatingActionButton = {
            if (isLoggedIn && book != null) {
                FAB(
                    text = "Editar Libro",
                    icon = Icons.Default.Edit,
                    onClick = { navigateToEdit(id) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator()
                }

                errorMessage != null -> {
                    Text(text = errorMessage!!, color = Color.Red)
                }

                book != null -> {
                    BookDetailBookSection(
                        book = book!!,
                        rating = randomRating,
                        isLoggedIn = isLoggedIn,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
