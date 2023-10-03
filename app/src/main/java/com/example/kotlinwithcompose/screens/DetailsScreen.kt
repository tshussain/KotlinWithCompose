package com.example.kotlinwithcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotlinwithcompose.LocalList
import com.example.kotlinwithcompose.layout.MainLayout

/* Display item from main list using the given index */
@Composable
fun DetailsScreen(index: Int) {
    val mainList = LocalList.current
    MainLayout(screenTitle = "Details") {
        Card(modifier=Modifier.width(100.dp).height(200.dp)) {
            Column {
                Text("Index: $index")
                Text(mainList[index].name)
                Text("Age:" + mainList[index].age)
            }
        }
    }
}