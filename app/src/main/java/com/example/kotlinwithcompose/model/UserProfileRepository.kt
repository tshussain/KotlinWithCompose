package com.example.kotlinwithcompose.model

import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    suspend fun saveProfile(oldName: String, profileData: ProfileData)
    suspend fun getProfile(name: String): Flow<ProfileData>
    suspend fun getProfiles(): Flow<List<ProfileData>>
    suspend fun delete(name: String)
}