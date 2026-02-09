package com.example.librarytrackerapp.ui.components.home

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.librarytrackerapp.ui.components.home.clases.navigationItem

@Composable
fun HomeScreenBottomBar(
    navigateToLogin: () -> Unit
) {
    val navigationItems: List<navigationItem> = listOf(
        navigationItem(menu = "Library", selectedIcon = Icons.Filled.Book, unselectedIcon = Icons.Outlined.Book, onClick = {}),
        navigationItem(menu = "Reading", selectedIcon = Icons.Filled.MenuBook, unselectedIcon = Icons.Outlined.MenuBook, onClick = {}),
        navigationItem(menu = "Wishlist", selectedIcon = Icons.Filled.Bookmark, unselectedIcon = Icons.Outlined.Bookmark, onClick = {}),
        navigationItem(menu = "Profile", selectedIcon = Icons.Filled.Person, unselectedIcon = Icons.Outlined.Person, onClick = { navigateToLogin() })
    )
    var selectedMenu by remember { mutableStateOf(navigationItems[0].menu) }

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.9f),
        tonalElevation = 8.dp,
        modifier = Modifier.height(70.dp)
    ) {
        navigationItems.forEach { navigationItem ->
            NavigationBarItem(
                selected = navigationItem.menu == selectedMenu,
                onClick = {
                    selectedMenu = navigationItem.menu
                    navigationItem.onClick()
                },
                icon = {
                    Icon(navigationItem.selectedIcon, contentDescription = navigationItem.menu)
                },
                label = {
                    Text(navigationItem.menu)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
