package com.example.kotlinwithcompose

import android.content.Context
import com.example.kotlinwithcompose.auth.AuthRepository
import com.example.kotlinwithcompose.auth.AuthRepositoryFirebase
import com.example.kotlinwithcompose.model.ProfileRepository
import com.example.kotlinwithcompose.model.ProfileRepositoryDataStore
import com.example.kotlinwithcompose.model.ProfileRepositoryFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

/** This module provides the specific object(s) we will inject */
class AppModule(
    private val appContext: Context,
    private val auth: FirebaseAuth
) {
    /* Create appropriate repository (backed by Firebase) on first use.
       Only one copy will be created during lifetime of the application. */
    val profileRepository : ProfileRepository by lazy {
        ProfileRepositoryFirestore(FirebaseFirestore.getInstance())
    }
    val authRepository : AuthRepository by lazy {
        AuthRepositoryFirebase(auth) // inject Firebase auth
    }
}