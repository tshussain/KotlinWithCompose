package com.example.kotlinwithcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AboutScreen(name: String) {
    val navController = LocalNavController.current
    Column() {
        Text("Welcome ${name}.  About Us")
        Button(onClick = { navController.navigate("ContactScreenRoute/Dave") }) {
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