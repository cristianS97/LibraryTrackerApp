package com.example.librarytrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.librarytrackerapp.ui.navigation.NavGraph
import com.example.librarytrackerapp.ui.theme.LibraryTrackerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LibraryTrackerAppTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
