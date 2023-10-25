package com.example.kotlinwithcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.kotlinwithcompose.auth.AuthRepositoryFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun CheckAuth() {
//    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
//    val coroutineScope = rememberCoroutineScope()
//
//    val snackbarHostState = remember { SnackbarHostState() } // Material 3 approach
//    val auth = FirebaseAuth.getInstance()
//
//    Column {
//        if (user == null) {
//            Text("Not logged in")
//            Button(onClick = {
//                coroutineScope.launch {
//                    try {
//                        val result = auth.signInWithEmailAndPassword("myname@name.com", "Abcd1234!").await()
//                    } catch (e: Exception) {
//                        snackbarHostState.showSnackbar("Authentication failed")
//                    }
//
//                    //val authResult = AuthRepositoryFirebase().signUp("myname@name.com", "Abcd1234!")
//                }
//            }) {
//                Text("Sign up via email")
//            }
//            Button(onClick = {
//                coroutineScope.launch {
//                    val authResult = AuthRepositoryFirebase().signIn("myname@name.com", "Abcd1234!")
//                }
//            }) {
//                Text("Sign in via email")
//            }
//        } else {
//            Text("Welcome ${user!!.email}")
//            Button(onClick = {
//                   AuthRepositoryFirebase().signOut()
//            }) {
//                Text("Sign out")
//            }
//        }
//    }
}