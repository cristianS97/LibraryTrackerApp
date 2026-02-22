package com.example.librarytrackerapp.ui.screens.bookdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.librarytrackerapp.ui.components.BookDetailConfirmDeleteDialog
import com.example.librarytrackerapp.ui.components.bookdetail.BookDetailBookSection
import com.example.librarytrackerapp.ui.components.bookdetail.BookDetailFAB
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
    val isAdmin by viewModel.isAdmin.observeAsState(initial = false)
    val deleteSuccess by viewModel.deleteSuccess.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState()
    val isMenuExpanded by viewModel.isMenuExpanded.observeAsState(initial = false)
    val showDeleteDialog by viewModel.showDeleteDialog.observeAsState(initial = false)
    val randomRating = ((0..50).random() * 0.1).let { "%.2f".format(it).toDouble() }


    LaunchedEffect(deleteSuccess) {
        if (deleteSuccess) {
            navigateToHome()
        }
    }

    Scaffold(
        topBar = {
            TopBarApp("Book Details", navigateToHome = navigateToHome)
        },
        floatingActionButton = {
            BookDetailFAB(
                isLoggedIn = isLoggedIn,
                book = book,
                isAdmin = isAdmin,
                isMenuExpanded = isMenuExpanded,
                onCloseMenu = { viewModel.closeMenu() },
                onOpenDialog = { viewModel.openDialog() },
                onAccionesClick = { if (isMenuExpanded) viewModel.closeMenu() else viewModel.openMenu() },
                editBook = { navigateToEdit(id) }
            )
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

        if (showDeleteDialog) {
            BookDetailConfirmDeleteDialog(
                title = book?.title ?: "",
                onCloseDialog = { viewModel.closeDialog() },
                onBookDelete = { viewModel.eliminarLibro(id) }
            )
        }
    }
}
