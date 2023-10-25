package com.example.kotlinwithcompose.auth

import com.example.kotlinwithcompose.model.ProfileData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun signIn(email: String, password: String): Boolean
    fun signOut()
    fun currentUser() : StateFlow<User?>
}