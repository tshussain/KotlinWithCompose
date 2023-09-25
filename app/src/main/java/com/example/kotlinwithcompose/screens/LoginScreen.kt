package com.example.kotlinwithcompose.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.kotlinwithcompose.layout.MainLayout

@Composable
fun LoginScreen(name: String) {
    val navController = LocalNavController.current
    MainLayout(screenTitle = "About") {
        Text("Welcome ${name}.  About Us")
        Button(onClick = { navController.navigate(Routes.Contact.go("Dave","New York"))}) {
            Text("Contact Us")
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
        Button(onClick = { navController.popBackStack("MainScreenRoute", false) }) {
            Text("Back Home")
        }
    }
}