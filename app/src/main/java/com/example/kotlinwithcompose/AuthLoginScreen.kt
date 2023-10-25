package com.example.kotlinwithcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinwithcompose.auth.AuthViewModel
import com.example.kotlinwithcompose.auth.AuthViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun AuthLoginScreen(authViewModel: AuthViewModel =
                                viewModel(factory= AuthViewModelFactory())
    ) {
    val userState = authViewModel.currentUser().collectAsState()

//    val isUserSignedOut = authViewModel.getAuthState().collectAsState().value

    val snackbarHostState = remember { SnackbarHostState() } // Material 3 approach
    val coroutineScope = rememberCoroutineScope()

    Column {
        if (userState.value == null) {
            Text("Not logged in")
            Button(onClick = {
//                coroutineScope.launch {
                    authViewModel.signUp("myname@name.com", "Abcd1234!")
//                    if (!result) {
//                        snackbarHostState.showSnackbar("Authentication failed")
//                    }
//                }
            }) {
                Text("Sign up via email")
            }
            Button(onClick = {
//                coroutineScope.launch {
                    authViewModel.signIn("myname@name.com", "Abcd1234!")
//                    if (!result) {
//                        snackbarHostState.showSnackbar("Registration failed")
//                    }
//                }
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
        }
    }
}