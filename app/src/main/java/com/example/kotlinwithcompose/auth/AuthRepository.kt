package com.example.kotlinwithcompose.auth

import com.example.kotlinwithcompose.model.ProfileData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

interface AuthRepository {
    // Return a StateFlow so that the composable can always update when
    //   the current authorized user status changes for any reason
    fun currentUser() : StateFlow<User?>
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun signIn(email: String, password: String): Boolean
    fun signOut() : Boolean
    suspend fun delete() : Boolean

}