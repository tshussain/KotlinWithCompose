package com.example.kotlinwithcompose.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kotlinwithcompose.MyApp
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _signInResult = MutableStateFlow<ResultAuth<Boolean>?>(null)
    private val _signUpResult = MutableStateFlow<ResultAuth<Boolean>?>(null)
    private val _signOutResult = MutableStateFlow<ResultAuth<Boolean>?>(null)
    private val _deleteAccountResult = MutableStateFlow<ResultAuth<Boolean>?>(null)

    val signInResult: StateFlow<ResultAuth<Boolean>?> = _signInResult
    val signUpResult: StateFlow<ResultAuth<Boolean>?> = _signUpResult
    val signOutResult: StateFlow<ResultAuth<Boolean>?> = _signOutResult
    val deleteAccountResult: StateFlow<ResultAuth<Boolean>?> = _deleteAccountResult

    // Return a StateFlow so that the composable can always update
    // based when the value changes
    fun currentUser(): StateFlow<User?> {
        return authRepository.currentUser()
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val success = authRepository.signUp(email, password)
                _signUpResult.value = ResultAuth.Success(success)
            } catch (e: FirebaseAuthException) {
                _signUpResult.value = ResultAuth.Failure(e)
            }
            // Reset the others since they are no longer applicable
            _signInResult.value = ResultAuth.Inactive
            _signOutResult.value = ResultAuth.Inactive
            _deleteAccountResult.value = ResultAuth.Inactive
        }
    }
    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val success = authRepository.signIn(email, password)
                _signInResult.value = ResultAuth.Success(success)
            } catch (e: FirebaseAuthException) {
                _signInResult.value = ResultAuth.Failure(e)
            }
            // Reset the others since they are no longer applicable
            _signUpResult.value = ResultAuth.Inactive
            _signOutResult.value = ResultAuth.Inactive
            _deleteAccountResult.value = ResultAuth.Inactive
        }
    }
    fun signOut() {
        try {
            val success = authRepository.signOut()
            _signOutResult.value = ResultAuth.Success(success)
        } catch (e: FirebaseAuthException) {
            _signOutResult.value = ResultAuth.Failure(e)
        }
        // Reset the others since they are no longer applicable
        _signInResult.value = ResultAuth.Inactive
        _signUpResult.value = ResultAuth.Inactive
        _deleteAccountResult.value = ResultAuth.Inactive
    }
    fun delete() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val success = authRepository.delete()
                _deleteAccountResult.value = ResultAuth.Success(success)
            } catch (e: FirebaseAuthException) {
                _deleteAccountResult.value = ResultAuth.Failure(e)
            }
            // Reset the others since they are no longer applicable
            _signInResult.value = ResultAuth.Inactive
            _signUpResult.value = ResultAuth.Inactive
            _signOutResult.value = ResultAuth.Inactive
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