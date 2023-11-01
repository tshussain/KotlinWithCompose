package com.example.kotlinwithcompose.model

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ProfileRepositoryFirestore (val db: FirebaseFirestore) : ProfileRepository {
    val dbProfile: CollectionReference = db.collection("Profile")
    val profileId = "main-profile"
    override suspend fun saveProfile(profileData: ProfileData) {
        // We are storing only a single profile at a time, so use a unique document name to refer to it
        dbProfile.document(profileId).set(profileData)
            .addOnSuccessListener {
                println("Profile saved.")
            }
            .addOnFailureListener { e ->
                println("Error saving profile: $e")
            }
    }

    override suspend fun getProfile(): Flow<ProfileData> = callbackFlow {

        val docRef = dbProfile.document("main-profile")
        val subscription = docRef.addSnapshotListener{ snapshot, error ->
               if (error != null) {
                    // An error occurred
                   println("Listen failed: $error")
                   return@addSnapshotListener
               }
                if (snapshot != null && snapshot.exists()) {
                    // The user document has data
                    val profile = snapshot.toObject(ProfileData::class.java)
                    if (profile != null) {
                        println("Real-time update to profile")
                        trySend(profile)
                    } else {
                        println("Profile is / has become null")
                        trySend(ProfileData()) // If there is no saved profile, then send a default object
                    }
                } else {
                    // The user document does not exist or has no data
                    println("Profile does not exist")
                    trySend(ProfileData()) // send default object
                }
            }
            awaitClose { subscription.remove() }
    }

    override suspend fun clear() {
        dbProfile.document(profileId)
            .delete()
            .addOnSuccessListener { println("Profile successfully deleted!") }
            .addOnFailureListener { error -> println("Error deleting profile: $error") }
    }
}