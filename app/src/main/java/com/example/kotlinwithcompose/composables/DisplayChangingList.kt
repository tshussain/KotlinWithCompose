package com.example.kotlinwithcompose.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DisplayChangingList(theList: List<String>,
                        add:(String?) -> Unit,
                        remove:(String) -> Unit) {

    LazyColumn {
        item() {
            Button(
                onClick = {add("New Item")},
            ) {
                Text(text = "Add Item")
            }
        }
        itemsIndexed(theList) { index, item ->
            Text(
                text = "#$index: $item",
                modifier = Modifier
                    .clickable { remove(item) }
                    .padding(16.dp)
            )
        }
    }
}