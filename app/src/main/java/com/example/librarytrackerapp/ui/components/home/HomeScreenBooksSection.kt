package com.example.librarytrackerapp.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarytrackerapp.domain.model.Book

@Composable
fun HomeScreenBooksSection(books: List<Book>, navigateToDetail: (Int) -> Unit) {
    Column() {
        Text(
            "RECENTLY ADDED",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(books) {
                HomeScreenBookDetail(
                    book = it,
                    navigateToDetail = navigateToDetail
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "You have ${books.size} books in your library",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        }
    }
}