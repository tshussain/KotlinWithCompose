package com.example.kotlinwithcompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyOtherScreen() {
    var name by rememberSaveable { mutableStateOf("") }
    Column() {
        // Example of conditional rendering
        if (name.isNotEmpty()) {
            Text("Welcome $name")
        }
        MyForm(setName = { name = it }) // passing a setter
        Spacer(modifier = Modifier.height(40.dp).width(20.dp))
        MyOtherForm(name = name, setName = { name = it }) // passing a setter
    }
}