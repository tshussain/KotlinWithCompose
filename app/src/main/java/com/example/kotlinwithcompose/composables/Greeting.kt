@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kotlinwithcompose.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }

    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Text("Welcome to my app")
        CountWithButton()
        TextField(
            value = name,
            onValueChange = { name = it },
            textStyle = TextStyle(textAlign = TextAlign.Center),
            label = { Text(text = "Please enter your name") }
        )
        MyForm()
        Card(
            onClick = { /* Do something */ },
            modifier = Modifier.size(width = 180.dp, height = 100.dp),
        ) {
            Box(Modifier.fillMaxSize()) {
                Text("Clickable", Modifier.align(Alignment.Center))
            }
        }
    }
}

