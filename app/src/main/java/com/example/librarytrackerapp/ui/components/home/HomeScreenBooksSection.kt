package com.example.librarytrackerapp.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.librarytrackerapp.domain.model.Book

@Composable
fun HomeScreenBooksSection(books: List<Book>) {
    Column() {
        Text("RECENTLY ADDED", color = MaterialTheme.colorScheme.onSurfaceVariant)
        LazyColumn() {
            items(books) {
                HomeScreenBookDetail(it)
            }
        }

    }
}