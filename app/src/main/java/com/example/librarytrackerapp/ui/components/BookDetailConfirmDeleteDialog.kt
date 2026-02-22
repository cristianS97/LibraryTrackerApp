package com.example.librarytrackerapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun BookDetailConfirmDeleteDialog(
    title: String,
    onCloseDialog: () -> Unit,
    onBookDelete: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onCloseDialog() },
        containerColor = MaterialTheme.colorScheme.surface,
        confirmButton = {
            TextButton(
                onClick = {
                    onCloseDialog()
                    onBookDelete()
                }
            ) {
                Text("Eliminar", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onCloseDialog() }
            ) {
                Text("Cancelar", color = MaterialTheme.colorScheme.primary)
            }
        },
        title = { Text("¿Eliminar libro?", color = MaterialTheme.colorScheme.onSurface) },
        text = {
            Text(
                "Esta acción no se puede deshacer. El libro '${title}' será borrado permanentemente.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        icon = {
            Icon(
                Icons.Default.Delete,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        }
    )
}