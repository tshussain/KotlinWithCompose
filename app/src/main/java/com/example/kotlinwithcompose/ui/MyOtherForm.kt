@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
// Note: Material 3 is still in process of being released, so it is "experimental"

package com.example.kotlinwithcompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MyOtherForm(name:String, setName: (String)->Unit) { // return type Unit is like "void" in Java
    Column {
        TextField(
            value = name,
            // "it" is a special keyword referring to the value entered in the textfield
            onValueChange = { setName(it) },
            textStyle = TextStyle(textAlign = TextAlign.Center),
            label = { Text(text = "Please enter your name") }
        )
    }
}