package com.example.kotlinwithcompose.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Stores a name value as a MutableStateFlow */
class MyViewModelSimplest : ViewModel() {
    // private UI state (MutableStateFlow)
    private val _nameFlow = MutableStateFlow<String>("")

    // public getter for the state (StateFlow)
    val nameFlow: StateFlow<String> get() = _nameFlow.asStateFlow()

    /** When we call setName() function nameFlow will change and
     *   every receiver of nameFlow will get the latest data.
     */
    fun setName(name: String) {
        _nameFlow.value = name
    }
}