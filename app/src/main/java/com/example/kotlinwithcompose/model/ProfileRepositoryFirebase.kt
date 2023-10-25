package com.example.kotlinwithcompose.model

import com.example.kotlinwithcompose.auth.AuthRepositoryFirebase
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryFirebase (val authRepository: AuthRepositoryFirebase) : ProfileRepository {

    override suspend fun saveProfile(profileData: ProfileData) {
        TODO("Not yet implemented")
    }

    override fun getProfile(): Flow<ProfileData> {
        // Access a Cloud Firestore instance from your Activity

        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}