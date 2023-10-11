package com.example.kotlinwithcompose.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyViewModelSimpler : ViewModel() {
    // private UI state (MutableStateFlow)
    private val _nameFlow = MutableStateFlow<String>("")
    private val _counterFlow = MutableStateFlow<Int>(0)

    // public getter for the state (StateFlow)
    val nameFlow: StateFlow<String> get() = _nameFlow.asStateFlow()
    val counterFlow: StateFlow<Int> get() = _counterFlow.asStateFlow()

    /** When we call setName() function nameFlow will change and
     *   every receiver of nameFlow will get the latest data.
     */
    fun setName(name: String) {
        _nameFlow.value = name
    }

    fun incrementCounter() {
        _counterFlow.value = _counterFlow.value + 1
    }
}
