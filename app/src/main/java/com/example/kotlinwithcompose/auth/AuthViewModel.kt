package com.example.kotlinwithcompose.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kotlinwithcompose.MyApp
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    // Return a StateFlow so that the composable can always update
    // based when the value changes
    fun currentUser(): StateFlow<User?> {
        return authRepository.currentUser()
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signUp(email, password)
        }
    }
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signIn(email, password)
        }
    }
    fun signOut() {
        authRepository.signOut()
    }
    fun delete() {
        viewModelScope.launch {
            authRepository.delete()
        }
    }
}

/* ViewModel Factory that will create our view model by injecting the
      authRepository from the module.
 */
class AuthViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(MyApp.appModule.authRepository) as T
    }
}