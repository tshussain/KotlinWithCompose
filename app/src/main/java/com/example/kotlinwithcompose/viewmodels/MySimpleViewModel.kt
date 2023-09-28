package com.example.kotlinwithcompose.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

private fun initialList() = List(10) { i -> "Item # $i" }

class MySimpleViewModel : ViewModel() {
    // Declare private mutable variable that can only be modified
    // within the class it is declared.
    private var _count by mutableIntStateOf(0)
    private val _items = initialList().toMutableStateList()

    // Declare a public immutable field and override its getter method.
    // Return the private property's value in the getter method.
    // When count is accessed, the get() function is called and
    // the value of _count is returned.
    val count: Int
        get() = _count
    val items: List<String>
        get() = _items

    fun increment() {
        _count +=1
    }
    fun add() {
        _items.add("Item # ${count+10}") // add initial size of list
        _count++
    }
    fun remove(item: String) {
        _items.remove(item)
    }

}