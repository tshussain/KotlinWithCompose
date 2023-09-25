package com.example.kotlinwithcompose.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.kotlinwithcompose.screens.LocalNavController
import com.example.kotlinwithcompose.screens.Routes

@Composable
fun SharedBottomBar() {
    val navController = LocalNavController.current
    BottomAppBar(
        actions = {
            IconButton(onClick = { navController.navigate(Routes.Main.route)}) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Go Home"
                )
            }
            IconButton(onClick = { navController.navigate(Routes.About.go("Franz"))}) {
                Icon(Icons.Filled.Info, contentDescription = "Go to About Us")
            }
            IconButton(onClick = { navController.navigate(Routes.Contact.go("Julie","Paris"))}) {
                Icon(Icons.Filled.Phone, contentDescription = "Go to Contact Us")
            }
        })
}
