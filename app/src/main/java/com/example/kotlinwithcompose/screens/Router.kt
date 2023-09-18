package com.example.kotlinwithcompose.screens

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinwithcompose.LocalNavController
import com.example.kotlinwithcompose.layout.MainLayout

@Composable
fun Router() {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = "MainScreenRoute",
        enterTransition = { slideInHorizontally() + expandHorizontally() },
        exitTransition = { slideOutVertically() + shrinkVertically() + fadeOut() }
    ) {
        composable("MainScreenRoute") { MainScreen(navController) }
        composable("AboutScreenRoute/{name}",
            enterTransition = { fadeIn() + expandIn() },
            exitTransition = { ExitTransition.None }) {
            AboutScreen(
                it.arguments?.getString("name") ?: ""
            )
        }
        composable("ContactScreenRoute/{name}") {
            ContactScreen(
                it.arguments?.getString("name") ?: ""
            )
        }
    }
}