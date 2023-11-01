package com.example.kotlinwithcompose.model

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun saveProfile(profileData: ProfileData)
    suspend fun getProfile(): Flow<ProfileData>
    suspend fun clear()
}