@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kotlinwithcompose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import com.example.kotlinwithcompose.LocalNavController

@Composable
fun MainLayout(
    content: @Composable () -> Unit
) {

    val navController = LocalNavController.current
    Scaffold(
        topBar = { TopAppBar(title = { Text("My App") }) },
        bottomBar = { BottomAppBar { Text("Copyright (c) 2023 CoolEntertainment, Inc.") } },
        floatingActionButton = { FloatingActionButton(onClick = {navController.navigate("MainScreenRoute")}) { Text("Home") } },
    ) {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}