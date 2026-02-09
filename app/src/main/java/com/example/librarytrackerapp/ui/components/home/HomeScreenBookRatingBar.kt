package com.example.librarytrackerapp.ui.components.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenBookRatingBar(
    rating: Double
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            val starIndex = index + 1
            val isFilled = rating >= starIndex
            val isHalf = !isFilled && rating >= starIndex - 0.5

            Icon(
                imageVector = when {
                    isFilled -> Icons.Filled.Star
                    isHalf -> Icons.AutoMirrored.Filled.StarHalf
                    else -> Icons.Filled.Star
                },
                contentDescription = null,
                tint = if (rating >= starIndex - 0.5) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                },
                modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(2.dp))
        }
        Spacer(Modifier.width(8.dp))
        Text(rating.toString(), color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}