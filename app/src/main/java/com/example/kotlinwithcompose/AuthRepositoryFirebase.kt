package com.example.kotlinwithcompose

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await


/*
https://firebase.google.com/docs/auth
 */
class AuthRepositoryFirebase {
//    suspend fun firebaseSignUpWithEmailAndPassword(
//        email: String, password: String
//    ) : Boolean {
//        return try {
//            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
//            return true;
//        } catch (e: Exception) {
//            return false;
//        }
//    }

    suspend fun signUp(username: String, password: String): AuthResult {
        return Firebase.auth.createUserWithEmailAndPassword(username, password).await()
    }

    suspend fun signIn(username: String, password: String): AuthResult {
        return Firebase.auth.signInWithEmailAndPassword(username, password).await()
    }

    fun signOut() {
        return Firebase.auth.signOut()
    }

    fun isLoggedIn() : Boolean {
        return Firebase.auth.currentUser != null
    }

    suspend fun getCurrentUser() : FirebaseUser? {
        return Firebase.auth.currentUser
    }

    suspend fun delete() {
        if (Firebase.auth.currentUser != null) {
            Firebase.auth.currentUser!!.delete()
        }
    }
}