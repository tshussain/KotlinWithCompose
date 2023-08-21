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
    // private UI state
    private val _uiState = MutableStateFlow(MyUiState())
    // public getter for the state
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

//    private var allNames: MutableSet<String> = mutableSetOf()

    init {
        resetState()
    }

    /*
     * Re-initializes the game data to restart the game.
     */
    fun resetState() {
//        allNames.clear()
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