package com.example.kotlinwithcompose.model

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinwithcompose.LocalViewModel
import com.example.kotlinwithcompose.layout.items
import com.example.kotlinwithcompose.ui.randomName

@Composable
fun MySimpleSavedScreen() {
    var myViewModel: MyViewModelSimpleSaved = LocalViewModel.current
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