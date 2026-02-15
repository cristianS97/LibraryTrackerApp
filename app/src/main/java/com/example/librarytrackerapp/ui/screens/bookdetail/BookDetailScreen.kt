package com.example.librarytrackerapp.ui.screens.bookdetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.librarytrackerapp.ui.components.home.HomeScreenBookRatingBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    id: Int,
    navigateToHome: () -> Unit,
    viewModel: BookDetailScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(id) {
        viewModel.obtenerLibro(id)
    }

    val scrollState = rememberScrollState()
    val book by viewModel.book.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState()
    val randomRating = ((0..50).random() * 0.1).let { "%.2f".format(it).toDouble() }
    val currentStatus by viewModel.bookStatus.observeAsState(BookStatus.READING)
    val currentRating by viewModel.userRating.observeAsState(0)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Default.ChevronLeft,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.clickable(
                                enabled = true,
                                onClick = { navigateToHome() })
                        )
                        Text(
                            "Book Details",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SubcomposeAsyncImage(
                            model = book!!.image,
                            contentDescription = book!!.title,
                            loading = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.LightGray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CloudDownload,
                                        contentDescription = "Cargando",
                                        tint = Color.Gray,
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
                            },
                            error = {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = "Error",
                                    tint = Color.Red
                                )
                            },
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(160.dp)
                                .height(240.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Text(
                            text = "${book?.title}",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 48.sp
                        )
                        Text(
                            text = "${book?.author}",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 24.sp
                        )
                        HomeScreenBookRatingBar(randomRating)
                        BookDetailStatusSelector(
                            selectedStatus = currentStatus,
                            onStatusSelected = { newStatus ->
                                viewModel.updateStatus(newStatus)
                            }
                        )
                        RatingSection(
                            rating = currentRating,
                            onRatingChange = { newRating ->
                                viewModel.onRatingChanged(newRating)
                            }
                        )
                        Text(
                            text = "Descripción",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 24.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = book?.description ?: "Sin descripción",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

enum class BookStatus(val label: String) {
    READ("Read"),
    READING("Reading"),
    PENDING("Pending")
}

@Composable
fun BookDetailStatusSelector(
    selectedStatus: BookStatus,
    onStatusSelected: (BookStatus) -> Unit
) {
    // Fondo del selector
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF1E2632)), // Color Slate 800/50 del diseño
        verticalAlignment = Alignment.CenterVertically
    ) {
        BookStatus.entries.forEach { status ->
            val isSelected = selectedStatus == status

            // Cada "Botón" del selector
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(4.dp) // Espaciado interno para que el seleccionado se vea flotando
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (isSelected) Color(0xFF135BEC) else Color.Transparent // Azul primary
                    )
                    .clickable { onStatusSelected(status) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = status.label,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else Color(0xFF94A3B8) // Blanco o Slate 400
                    )
                )
            }
        }
    }
}

@Composable
fun RatingSection(
    rating: Int,
    onRatingChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF135BEC).copy(alpha = 0.1f) // Primary 10%
        ),
        border = BorderStroke(1.dp, Color(0xFF135BEC).copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Rating",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Fila de 5 estrellas
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                (1..5).forEach { index ->
                    val isSelected = index <= rating
                    Icon(
                        imageVector = if (isSelected) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null // Sin efecto de onda circular para que se sienta más limpio
                            ) { onRatingChange(index) },
                        tint = if (isSelected) Color(0xFF135BEC) else Color.White.copy(alpha = 0.2f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "TAP TO RATE",
                style = MaterialTheme.typography.labelSmall.copy(
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White.copy(alpha = 0.4f)
                )
            )
        }
    }
}
