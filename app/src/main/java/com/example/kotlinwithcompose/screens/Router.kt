package com.example.kotlinwithcompose.screens

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

val LocalNavController = compositionLocalOf<NavHostController> { error("No NavController found!") }

sealed class Routes(val route:String)  {
    object Main : Routes("MainScreenRoute")
    object About : Routes("AboutScreenRoute/{name}") {
        fun go(name: String) = "AboutScreenRoute/$name"
    }
    object Contact: Routes("ContactScreenRoute/{name}/{location}") {
        fun go(name: String, location: String) = "ContactScreenRoute/$name/$location"
    }
}
@Composable
fun Router() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {

        NavHost(navController = navController, startDestination = "MainScreenRoute",
            enterTransition = { slideInHorizontally() + expandHorizontally() },
            exitTransition = { slideOutVertically() + shrinkVertically() + fadeOut() }
        ) {
            composable(Routes.Main.route) { MainScreen() }
            composable(Routes.About.route,
                enterTransition = { fadeIn() + expandIn() },
                exitTransition = { ExitTransition.None }) {
                AboutScreen(
                    it.arguments?.getString("name") ?: ""
                )
            }
            composable(Routes.Contact.route) {
                val name = it.arguments?.getString("name") ?: ""
                val location = it.arguments?.getString("location") ?: ""

                ContactScreen(name, location)
            }
        }
    }
}