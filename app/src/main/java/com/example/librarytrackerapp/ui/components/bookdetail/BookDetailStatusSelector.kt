package com.example.librarytrackerapp.ui.components.bookdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.librarytrackerapp.ui.components.bookdetail.clases.BookStatus

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