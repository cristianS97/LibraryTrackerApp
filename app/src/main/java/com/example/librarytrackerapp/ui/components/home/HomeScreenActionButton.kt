package com.example.librarytrackerapp.ui.components.home

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun HomeScreenActionButton(navigateToCreate: () -> Unit) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        shape = CircleShape,
        onClick = { navigateToCreate() }
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}
