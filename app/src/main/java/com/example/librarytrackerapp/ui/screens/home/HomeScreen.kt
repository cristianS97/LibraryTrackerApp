package com.example.librarytrackerapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.librarytrackerapp.ui.components.home.HomeScreenActionButton
import com.example.librarytrackerapp.ui.components.home.HomeScreenAuthorsModalSheet
import com.example.librarytrackerapp.ui.components.home.HomeScreenBooksSection
import com.example.librarytrackerapp.ui.components.home.HomeScreenBottomBar
import com.example.librarytrackerapp.ui.components.home.HomeScreenSearchBar
import com.example.librarytrackerapp.ui.components.home.HomeScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit
) {
    var showFilterSheet by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedAuthor by remember { mutableStateOf("") }
    val books by viewModel.books.observeAsState()

    val authorsList = listOf("All", "Tolkien", "Stephen King", "George R.R. Martin", "J.K. Rowling")

    Scaffold(
        topBar = {
            HomeScreenTopBar(onFilterClick = { showFilterSheet = true })
        },
        floatingActionButton = { HomeScreenActionButton() },
        bottomBar = { HomeScreenBottomBar(
            navigateToLogin = navigateToLogin
        ) },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            HomeScreenSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it }
            )
            if (books?.isNotEmpty() ?: false) {
                HomeScreenBooksSection(books!!)
            }
        }

        if (showFilterSheet) {
            HomeScreenAuthorsModalSheet(
                authors = authorsList,
                onDismiss = { showFilterSheet = false },
                onAuthorSelected = {
                    selectedAuthor = it
                    showFilterSheet = false
                }
            )
        }
    }
}
