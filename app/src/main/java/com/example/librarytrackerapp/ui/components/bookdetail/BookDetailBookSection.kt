package com.example.librarytrackerapp.ui.components.bookdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.librarytrackerapp.domain.model.Book
import com.example.librarytrackerapp.ui.components.bookdetail.clases.BookStatus
import com.example.librarytrackerapp.ui.components.common.BookImage
import com.example.librarytrackerapp.ui.components.home.HomeScreenBookRatingBar
import com.example.librarytrackerapp.ui.screens.bookdetail.BookDetailScreenViewModel

@Composable
fun BookDetailBookSection(
    book: Book,
    rating: Double,
    viewModel: BookDetailScreenViewModel,
    isLoggedIn: Boolean
) {
    val scrollState = rememberScrollState()
    val currentStatus by viewModel.bookStatus.observeAsState(BookStatus.READING)
    val currentRating by viewModel.userRating.observeAsState(0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookImage(book.image, book.title, "detail")
        Text(
            text = book.title,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 48.sp,
            modifier = Modifier.padding(top = 12.dp)
        )
        Text(
            text = book.author,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        HomeScreenBookRatingBar(rating)
        if(isLoggedIn) {
            BookDetailStatusSelector(
                selectedStatus = currentStatus,
                onStatusSelected = { newStatus ->
                    viewModel.updateStatus(newStatus)
                }
            )
            BookDetailRatingSection(
                rating = currentRating,
                onRatingChange = { newRating ->
                    viewModel.onRatingChanged(newRating)
                }
            )
        }
        Text(
            text = "Descripción",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            textAlign = TextAlign.Start
        )
        Text(
            text = book.description,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 18.sp
        )
    }
}