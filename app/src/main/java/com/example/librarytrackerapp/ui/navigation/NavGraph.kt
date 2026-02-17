package com.example.librarytrackerapp.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.librarytrackerapp.ui.screens.bookdetail.BookDetailScreen
import com.example.librarytrackerapp.ui.screens.createbook.CreateBookScreen
import com.example.librarytrackerapp.ui.screens.home.HomeScreen
import com.example.librarytrackerapp.ui.screens.login.LoginScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home,
        // Animación cuando entras a una pantalla (desliza desde la derecha)
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(400)
            ) + fadeIn(animationSpec = tween(400))
        },
        // Animación cuando sales de una pantalla hacia adelante
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(400)
            ) + fadeOut(animationSpec = tween(400))
        },
        // Animación cuando vuelves atrás (desliza desde la izquierda)
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(400)
            ) + fadeIn(animationSpec = tween(400))
        },
        // Animación cuando la pantalla actual se cierra hacia atrás
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(400)
            ) + fadeOut(animationSpec = tween(400))
        }
    ) {
        composable<Screen.Home> {
            HomeScreen(
                navigateToLogin = {
                    navController.navigate(Screen.Login)
                },
                navigateToDetail = { id ->
                    navController.navigate(Screen.BookDetailScreen(id))
                },
                navigateToCreate = {
                    navController.navigate(Screen.CreateBook)
                }
            )
        }
        composable<Screen.Login> {
            LoginScreen(
                navigateToHome = {
                    navController.navigate(Screen.Home)
                }
            )
        }
        composable<Screen.BookDetailScreen> { navBackStackEntry ->
            val data = navBackStackEntry.toRoute<Screen.BookDetailScreen>()
            BookDetailScreen(
                id = data.id,
                navigateToHome = {
                    navController.navigate(Screen.Home)
                }
            )
        }
        composable<Screen.CreateBook> {
            CreateBookScreen(
                navigateToHome = {
                    navController.navigate(Screen.Home)
                }
            )
        }
    }
}