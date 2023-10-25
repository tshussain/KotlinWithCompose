package com.example.kotlinwithcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinwithcompose.auth.AuthViewModel
import com.example.kotlinwithcompose.auth.AuthViewModelFactory

@Composable
fun AuthLoginScreen(authViewModel: AuthViewModel =
                                viewModel(factory= AuthViewModelFactory())
    ) {
    val userState = authViewModel.currentUser().collectAsState()

    Column {
        if (userState.value == null) {
            Text("Not logged in")
            Button(onClick = {
                authViewModel.signUp("myname2@name.com", "Abcd1234!")
            }) {
                Text("Sign up via email")
            }
            Button(onClick = {
                authViewModel.signIn("myname2@name.com", "Abcd1234!")
            }) {
                Text("Sign in via email")
            }
        } else {
            if (userState.value==null)
                Text("Please sign in")
            else
                Text("Welcome ${userState.value!!.email}")
            Button(onClick = {
                authViewModel.signOut()
            }) {
                Text("Sign out")
            }
            Button(onClick = {
                authViewModel.delete()
            }) {
                Text("Delete account")
            }
        }
    }
}