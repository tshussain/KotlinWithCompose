package com.example.kotlinwithcompose.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ProfileRepositoryFirestore (val auth: FirebaseAuth, val db: FirebaseFirestore) : ProfileRepository {
    val dbProfile: CollectionReference = db.collection("Profile")
    override suspend fun saveProfile(profileData: ProfileData) {
        dbProfile.add(profileData)
    }

    override suspend fun getProfile(): Flow<ProfileData> =
        callbackFlow {
            val successListener = dbProfile.get().addOnSuccessListener { value ->
                val result =
                    if (value != null) {
                        val data = value.documents.map { doc ->
                            doc.toObject(ProfileData::class.java)
                        }
                        if (data.isNotEmpty()) {
                            data[0]
                        } else {
                            null
                        }
                    } else {
                        null
                    }
                if (result != null) {
                    trySend(result)
                }
            }
            awaitClose() {
            }
        }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }
}