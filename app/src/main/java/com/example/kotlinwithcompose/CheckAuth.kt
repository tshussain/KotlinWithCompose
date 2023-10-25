package com.example.kotlinwithcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun CheckAuth() {
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    val coroutineScope = rememberCoroutineScope()

    Column {
        if (user == null) {
            Text("Not logged in")
            Button(onClick = {
                coroutineScope.launch {
                    val authResult = AuthRepositoryFirebase().signUp("myname@name.com", "Abcd1234!")
                }
            }) {
                Text("Sign up via email")
            }
            Button(onClick = {
                coroutineScope.launch {
                    val authResult = AuthRepositoryFirebase().signIn("myname@name.com", "Abcd1234!")
                }
            }) {
                Text("Sign in via email")
            }
        } else {
            Text("Welcome ${user!!.email}")
            Button(onClick = {
                   AuthRepositoryFirebase().signOut()
            }) {
                Text("Sign out")
            }
        }
    }
}