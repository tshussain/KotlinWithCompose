@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kotlinwithcompose.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MyForm() {
    var nameValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var ageValue by rememberSaveable { mutableStateOf("") }
    var loggedInStatus by rememberSaveable { mutableStateOf(false) }

    Column {
        if (!loggedInStatus) {
            TextField(
                value = nameValue,
                onValueChange = { nameValue = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text(text = "Please enter your username (Must be non-empty)") }
            )
            Spacer(modifier = Modifier
                .height(20.dp)
                .width(20.dp))
            TextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text(text = "Please enter your password (Must be at least 8 characters long)") }
            )
            Spacer(modifier = Modifier
                .height(20.dp)
                .width(20.dp))
            TextField(
                value = ageValue,
                onValueChange = { ageValue = it },
                textStyle = TextStyle(textAlign = TextAlign.Center),
                label = { Text(text = "Please enter your age (Must be 18 or older)") }
            )

            if (!nameValue.isEmpty() && !passwordValue.isEmpty()
                && passwordValue.length >= 8 && !ageValue.isEmpty()
                && ageValue.toInt() >= 18
            ) {
                Button(onClick = { loggedInStatus = true }) {
                    Text("Signup")
                }
            }
        }
        else {
            Text("Welcome $nameValue. You are $ageValue years old.")
        }
    }
}