package com.example.kotlinwithcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/** This helper function will allow us to inject dependencies into
 *    the various ViewModels in our code.
 */
fun <VM: ViewModel> viewModelFactoryHelper(initializerLambda: () -> VM) : ViewModelProvider.Factory {
    return object: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initializerLambda() as T
        }
    }
}