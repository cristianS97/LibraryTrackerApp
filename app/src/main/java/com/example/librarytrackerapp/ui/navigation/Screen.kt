package com.example.librarytrackerapp.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Home

    @Serializable
    object Login

    @Serializable
    data class BookDetailScreen(val id: Int)
}