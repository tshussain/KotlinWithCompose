package com.example.kotlinwithcompose.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

val LocalNavController = compositionLocalOf<NavController> { error("No NavController found!") }
@Composable
fun Router() {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = "MainScreenRoute") {
            composable("MainScreenRoute") { MainScreen(navController) }
            composable("AboutScreenRoute") { AboutScreen() }
            composable("ContactScreenRoute") { ContactScreen() }
        }
    }
}