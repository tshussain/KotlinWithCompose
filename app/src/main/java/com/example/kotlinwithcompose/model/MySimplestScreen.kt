package com.example.kotlinwithcompose.model

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MySimplestScreen(myViewModel: MyViewModelSimplest = viewModel()) {
    // collectAsState() collects values from this StateFlow and represents its latest
    //  value via State. The StateFlow.value is used as an initial value.
    //  Every time there would be new value posted into the StateFlow the
    //  returned State will be updated causing recomposition of every State.value usage.
    val name by myViewModel.nameFlow.collectAsState()

    Column {
        if (name != null) {
            Text(text = "Welcome ${name}")
        }
        Button(
            onClick = { myViewModel.setName(name + "x") }
        ) {
            Text(
                text = "Append To Name",
                fontSize = 16.sp
            )
        }
    }
}