package com.example.kotlinwithcompose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.random.Random

/* Composable that gets all state information from its view model. */
@Composable
fun MyScreen(myViewModel: MyViewModel = viewModel()) {
    val myUiState by myViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.height(200.dp)
    ) {
        Text(
            text = myUiState.currentName,
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { myViewModel.addName(randomName()) }
        ) {
            Text(
                text = "Add Name",
                fontSize = 16.sp
            )
        }

        LazyColumn(
            modifier = Modifier.height(100.dp),
        ) {
            if (myUiState != null && myUiState.allNames != null && myUiState.allNames.isNotEmpty()) {
                items(items = myUiState.allNames.toList()) {
                    Text(text = it)
                }
            }
        }
    }
}

/* Generate a random name.  Shows the use of repeat loop in Kotlin */
fun randomName(): String {
    val numChar = Random.nextInt(0,5)
    if (numChar == 0) {
        return "Jane"
    }
    var name= ""+('A'..'Z').random()
    repeat (numChar) {
        name += ('a'..'z').random()
    }
    return name
}
