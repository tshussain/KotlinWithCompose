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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/** We need to use this special remember function so that the list will
 *    be "rememberSaveable" upon phone rotation, etc. */
@Composable
fun <T: Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(
        saver = listSaver(
            save = { stateList ->
                if (stateList.isNotEmpty()) {
                    val first = stateList.first()
                    if (!canBeSaved(first)) {
                        throw IllegalStateException("${first::class} cannot be saved. By default only types which can be stored in the Bundle class can be saved.")
                    }
                }
                stateList.toList()
            },
            restore = { it.toMutableStateList() }
        )
    ) {
        elements.toList().toMutableStateList()
    }
}

@Composable
fun ChangingList() {
    val todoList = rememberMutableStateListOf<String>() // use this to get rememberSaveable behavior

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