@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kotlinwithcompose.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.kotlinwithcompose.screens.LocalNavController
import com.example.kotlinwithcompose.screens.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedTopBar(screenTitle: String){
    val navController = LocalNavController.current
    CenterAlignedTopAppBar(title = { Text(screenTitle) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp()}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go Back"
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(Routes.Register.Signup.route) }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "SignUp"
                )
            }
            IconButton(onClick = { navController.navigate(Routes.Register.Login.go("Sarah")) }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Menu"
                )
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        )
}