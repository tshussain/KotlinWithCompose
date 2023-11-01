package com.example.kotlinwithcompose.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kotlinwithcompose.MyApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/** Simple view model that keeps track of a single value (count in this case) */
class UserProfileViewModel(private val userProfileRepository: UserProfileRepository) : ViewModel() {
    // private UI state (MutableStateFlow)
    private val _activeProfile = MutableStateFlow(ProfileData())
    // public getter for the state (StateFlow)
    val activeProfile: StateFlow<ProfileData> = _activeProfile.asStateFlow()

    private val _allProfiles = MutableStateFlow(listOf<ProfileData>())
    // public getter for the state (StateFlow)
    val allProfiles: StateFlow<List<ProfileData>> = _allProfiles.asStateFlow()

    init {
        viewModelScope.launch {
            userProfileRepository.getProfiles().collect { allProfiles ->
                _allProfiles.value = allProfiles
            }
        }
    }

    /** Changes the active profile to the profile with the indicated name */
    fun selectProfile(name: String) {
        viewModelScope.launch {
            userProfileRepository.getProfile(name).collect { profileData ->
                _activeProfile.value = profileData
            }
        }
    }

    fun getAllProfiles() {
        // Start collecting the data from the data store when the ViewModel is created.
        viewModelScope.launch {
            userProfileRepository.getProfiles().collect { allProfiles ->
                _allProfiles.value = allProfiles
            }
        }
    }

    fun setName(newName: String) {
        viewModelScope.launch {
            if (_activeProfile == null || _activeProfile.value.name.isEmpty()) {
                val newProfile = ProfileData(newName)
                _activeProfile.update { newProfile }
                userProfileRepository.saveProfile(newName, newProfile)
            } else {
                var oldName = _activeProfile.value.name
                _activeProfile.update { it.copy(name = newName) }
                userProfileRepository.saveProfile(oldName, _activeProfile.value)
            }
        }
    }


    /* Increments the value of the counter stored in the state flow */
    fun increment() {
        viewModelScope.launch {
            var count = _activeProfile.value.counter;
            _activeProfile.update { currentState ->
                currentState.copy(counter = count + 1)
            }
            userProfileRepository.saveProfile(_activeProfile.value.name, _activeProfile.value)
        }
    }

    fun deleteProfile(name: String) {
        viewModelScope.launch {
            userProfileRepository.delete(name)
        }
    }

}

/* ViewModel Factory that will create our view model by injecting the
      ProfileDataStore from the module.
 */
class UserProfileViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserProfileViewModel(MyApp.appModule.userProfileRepository) as T
    }
}