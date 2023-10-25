package com.example.kotlinwithcompose.auth

sealed class ResultAuth<out T> {
    data class Success<out T>(val data: T) : ResultAuth<T>()
    data class Failure(val exception: Throwable) : ResultAuth<Nothing>()
    object Inactive : ResultAuth<Nothing>()
    object InProgress : ResultAuth<Nothing>()

}