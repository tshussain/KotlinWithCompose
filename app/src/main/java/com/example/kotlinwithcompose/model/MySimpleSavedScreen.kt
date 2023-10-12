package com.example.kotlinwithcompose.model

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

/* This constructor will new up our view model using injection as appropriate (in Factory) */
@Composable
fun MySimpleSavedScreen(myViewModel: MyViewModelSimpleSaved =
                            viewModel(factory=MyViewModelSimpleSavedFactory())) {
    val myUiState by myViewModel.uiState.collectAsState()

    Column {
        Button(
            onClick = { myViewModel.setName(myUiState.name + "x") }
        ) {
            Text(
                text = "Append to Name",
                fontSize = 16.sp
            )
        }
        Button(
            onClick = { myViewModel.increment() }
        ) {
            Text(
                text = "Increment Counter",
                fontSize = 16.sp
            )
        }
        if (myUiState != null) {
            Text(text = "Name: ${myUiState.name}")
            Text(text = "Counter value ${myUiState.counter}")
        }
    }
}