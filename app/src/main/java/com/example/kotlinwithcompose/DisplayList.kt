package com.example.kotlinwithcompose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/* Stateful */
@Composable
fun DisplayList() {
    val idList = remember { List<String>(100) { "Item $it" } }
    DisplayGivenList(idList = idList)
}

/* Stateless */
@Composable
fun DisplayGivenList(idList: List<String>) {
    LazyColumn {
        items(idList) { id ->
            Text(text = "" + id)
        }
    }
}
