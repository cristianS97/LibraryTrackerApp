package com.example.librarytrackerapp.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object Home
}