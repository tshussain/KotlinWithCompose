package com.example.kotlinwithcompose.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyUiState()) // private UI state
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow() // public getter for the state

    init {
        resetState()
    }

    /* Initialize the state to empty the list */
    fun resetState() {
        _uiState.value = MyUiState("", emptyList())
    }

    fun addName(name:String) {
        var allNames : MutableSet<String> = _uiState.value.allNames.toMutableSet()
        if (!allNames.contains(name)) {
            allNames.add(name)
            _uiState.update { currentState ->
                currentState.copy(currentName = name, allNames = allNames.toList())
            }
        }
    }

}