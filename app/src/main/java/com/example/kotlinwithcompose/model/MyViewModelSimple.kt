package com.example.kotlinwithcompose.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SimpleUiState(
    var name : String = "",
    var counter: Int = 0
)

/** Simple view model that keeps track of a single value (count in this case) */
class MyViewModelSimple : ViewModel() {
    // private UI state (MutableStateFlow)
    private val _uiState = MutableStateFlow(SimpleUiState())
    // public getter for the state (StateFlow)
    val uiState: StateFlow<SimpleUiState> = _uiState.asStateFlow()

//    /* Method called when ViewModel is first created */
//    init {
//        resetState()
//    }
//
//    /* Initialize the state to empty the list */
//    fun resetState() {
//        _uiState.value = SimpleUiState(0)
//    }

    fun setName(newName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(name = newName) }
        }
    }

    /* Increments the value of the counter stored in the state flow */
    fun increment() {
        viewModelScope.launch {
            var count = _uiState.value.counter;
            _uiState.update { currentState ->
                currentState.copy(counter = count + 1)
            }
        }
    }

}