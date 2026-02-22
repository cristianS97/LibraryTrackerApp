package com.example.librarytrackerapp.ui.components.bookdetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.librarytrackerapp.domain.model.Book

@Composable
fun BookDetailFAB(
    isLoggedIn: Boolean,
    book: Book?,
    isAdmin: Boolean,
    isMenuExpanded: Boolean,
    onCloseMenu: () -> Unit,
    onOpenDialog: () -> Unit,
    onAccionesClick: () -> Unit,
    editBook: () -> Unit
) {
    if (isLoggedIn && book != null) {
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // --- LÓGICA PARA ADMIN (Menú Desplegable) ---
            if (isAdmin) {
                // Botones secundarios que aparecen al expandir
                AnimatedVisibility(
                    visible = isMenuExpanded,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Botón de Eliminar
                        SmallFloatingActionButton(
                            onClick = {
                                onCloseMenu()
                                onOpenDialog()
                            },
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.error
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar Libro")
                        }

                        // Botón de Editar (dentro del menú admin)
                        SmallFloatingActionButton(
                            onClick = {
                                onCloseMenu()
                                editBook()
                            },
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar Libro")
                        }
                    }
                }

                // Botón Principal de Acciones para Admin
                ExtendedFloatingActionButton(
                    text = { Text(if (isMenuExpanded) "Cerrar" else "Acciones") },
                    icon = {
                        Icon(
                            if (isMenuExpanded) Icons.Default.Close else Icons.Default.Settings,
                            contentDescription = null
                        )
                    },
                    onClick = { onAccionesClick() },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )

            } else {
                // --- LÓGICA PARA USUARIO NORMAL (Botón simple) ---
                ExtendedFloatingActionButton(
                    text = { Text("Editar Libro") },
                    icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.primary,
                    onClick = { editBook() }
                )
            }
        }
    }
}