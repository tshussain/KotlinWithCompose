package com.example.kotlinwithcompose.model

import android.app.Application
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/** Simple view model that keeps track of a single value (count in this case) */
class MyViewModelSimpleSaved(private var application: Application) : ViewModel() {
    // private UI state (MutableStateFlow)
    private val _uiState = MutableStateFlow(ProfileData())
    // public getter for the state (StateFlow)
    val uiState: StateFlow<ProfileData> = _uiState.asStateFlow()

    private val profileDataStore = ProfileDataStore(application)

    /* Method called when ViewModel is first created */
    init {
        // Start collecting the data from the data store when the ViewModel is created.
        viewModelScope.launch {
            profileDataStore.getFromDataStore().collect { profileData ->
                _uiState.value = profileData
            }
        }
    }

    fun setName(newName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(name = newName) }
            profileDataStore.saveToDataStore(_uiState.value)
        }
    }


    /* Increments the value of the counter stored in the state flow */
    fun increment() {
        viewModelScope.launch {
            var count = _uiState.value.counter;
            _uiState.update { currentState ->
                currentState.copy(counter = count + 1)
            }
            profileDataStore.saveToDataStore(_uiState.value)
        }
    }

}