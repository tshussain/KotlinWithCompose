package com.example.kotlinwithcompose.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CountWithButton() {
    Column(modifier = Modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }

        Text("You've had ${count} glasses.")
        ElevatedButton(onClick = { count++ },
            Modifier.padding(top = 8.dp)) {
            Text("Add one")
        }
    }
}