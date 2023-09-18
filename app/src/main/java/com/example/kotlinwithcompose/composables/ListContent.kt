package com.example.kotlinwithcompose.composables
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListContent(onItemClick: (String) -> Unit) {
    val items : List<String> = remember { List(100) { "Item $it" } }

    LazyColumn {
        itemsIndexed(items) { index, item ->
            Text(
                text = "#$index: $item",
                modifier = Modifier
                    .clickable { onItemClick(item) }
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}