package com.example.kotlinwithcompose.ui

data class MyUiState (
    val currentName: String = "",
    val allNames: Collection<String> = emptyList()
)