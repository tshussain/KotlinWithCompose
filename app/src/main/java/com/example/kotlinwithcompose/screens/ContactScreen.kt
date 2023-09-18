package com.example.kotlinwithcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ContactScreen() {
    val navController = LocalNavController.current
    Column() {
        Text("Contact Us")
        Button(onClick = { navController.navigate("AboutScreenRoute") }) {
            Text("About Us")
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
        Button(onClick = { navController.popBackStack("MainScreenRoute", false) }) {
            Text("Back Home")
        }
    }
}