package com.example.librarytrackerapp.ui.components.home.clases

import androidx.compose.ui.graphics.vector.ImageVector

data class navigationItem(
    val menu: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val onClick: () -> Unit
)
