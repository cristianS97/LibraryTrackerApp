package com.example.librarytrackerapp.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun BookImage(image: String, title: String, location: String) {
    val imageWidth = when(location) {
        "home" -> 80.dp
        "detail" -> 160.dp
        else -> 0.dp
    }
    val imageHeight = when(location) {
        "home" -> 120.dp
        "detail" -> 240.dp
        else -> 0.dp
    }
    SubcomposeAsyncImage(
        model = image,
        contentDescription = title,
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
            .width(imageWidth)
            .height(imageHeight)
            .clip(RoundedCornerShape(8.dp))
    )
}