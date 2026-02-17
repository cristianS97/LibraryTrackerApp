package com.example.librarytrackerapp.ui.components.createedit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEditBookTopBar(title: String, navigateToHome: () -> Unit) {
    // 1. Estado para guardar el ancho medido
    var leftSectionWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 2. Sección izquierda que se mide a sí misma
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            // Convertimos los píxeles medidos a DP
                            leftSectionWidth =
                                with(density) { coordinates.size.width.toDp() }
                        }
                        .clickable { navigateToHome() }
                ) {
                    Icon(Icons.Default.ChevronLeft, contentDescription = null)
                    Text("Cancel", fontSize = 16.sp)
                }
                // 3. Título centrado
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                    // style = MaterialTheme.typography.titleMedium
                )
                // 4. Box que replica exactamente el ancho medido
                Box(
                    modifier = Modifier.width(leftSectionWidth)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}
