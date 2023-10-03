package com.example.kotlinwithcompose.screens

import android.view.Display
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinwithcompose.composables.DisplayChangingList
import com.example.kotlinwithcompose.viewmodels.MySimpleViewModel


/* Composable that gets all state information from its view model. */
@Composable
fun MySimpleScreen(myViewModel: MySimpleViewModel = viewModel()) {
    Column {
        Button(
            onClick = {
                myViewModel.increment()
                      },
        ) {
            Text(text = "Increment")
        }
        Text("Total items added by user: ${myViewModel.count}")
        DisplayChangingList(theList = myViewModel.items, add = myViewModel::add,
            remove = myViewModel::remove)
    }
}