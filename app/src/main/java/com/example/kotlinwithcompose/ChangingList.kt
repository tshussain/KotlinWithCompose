package com.example.kotlinwithcompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChangingList() {
    val todoList = rememberSaveable { mutableStateListOf<String>() }

    LazyColumn {
        item() {
            Button(
                onClick = {todoList.add("Do this ${todoList.size}")},
            ) {
                Text(text = "Add Item")
            }
        }
        itemsIndexed(todoList) { index, item ->
            Text(
                text = "#$index: $item",
                modifier = Modifier
                    .clickable { todoList.remove(item) }
                    .padding(16.dp)
            )
        }
    }
}