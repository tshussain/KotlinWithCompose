package com.example.kotlinwithcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.kotlinwithcompose.layout.MainLayout

@Composable
fun ContactScreen(name: String) {
    val navController = LocalNavController.current
    MainLayout() {
        Column() {
            Text("Contact Us, ${name}.")
            Button(onClick = { navController.navigate("AboutScreenRoute/Jane") }) {
                Text("About Us")
            }
            Button(onClick = { navController.navigate("MainScreenRoute") }) {
                Text("Go Home")
            }
            if (navController.previousBackStackEntry != null) {
                Button(onClick = { navController.navigateUp() }) {
                    Text("Back")
                }
            }
            Button(onClick = { navController.popBackStack("MainScreenRoute", false) }) {
                Text("Back Home")
            }
        }
    }
}