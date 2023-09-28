@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kotlinwithcompose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kotlinwithcompose.screens.LocalNavController

@Composable
fun MainLayout(
    screenTitle: String,
    content: @Composable () -> Unit
) {

    val navController = LocalNavController.current
    Scaffold(
        topBar = { SharedTopBar(screenTitle) },
        bottomBar = { SharedBottomBar2() },
   ) {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}