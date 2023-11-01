package com.example.kotlinwithcompose.model

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserProfileRepositoryFirestore (val db: FirebaseFirestore) : UserProfileRepository {
    val dbUserProfiles: CollectionReference = db.collection("UserProfiles")
    override suspend fun saveProfile(oldName: String, profileData: ProfileData) {
        // We are storing only a single profile at a time, so use a unique document name to refer to it
        dbUserProfiles.document(oldName).set(profileData)
            .addOnSuccessListener {
                println("Profile saved.")
            }
            .addOnFailureListener { e ->
                println("Error saving profile: $e")
            }
    }

    override suspend fun getProfile(name: String): Flow<ProfileData> = callbackFlow {

        val docRef = dbUserProfiles.document(name)
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

    override suspend fun delete(name:String) {
        dbUserProfiles.document(name)
            .delete()
            .addOnSuccessListener { println("Profile $name successfully deleted!") }
            .addOnFailureListener { error -> println("Error deleting profile $name: $error") }
    }

    override suspend fun getProfiles(): Flow<List<ProfileData>> = callbackFlow {

        // Listen for changes on entire collection
        val subscription = dbUserProfiles.addSnapshotListener{ snapshot, error ->
            if (error != null) {
                // An error occurred
                println("Listen failed: $error")
                return@addSnapshotListener
            }
            if (snapshot != null) {
                // The collection has documents, so convert them all to ProfileData objects
                val profiles = snapshot.toObjects(ProfileData::class.java)
                if (profiles != null) {
                    println("Real-time update to profile")
                    trySend(profiles)
                } else {
                    println("Profiles has become null")
                    trySend(listOf<ProfileData>()) // If there is no saved profile, then send a default object
                }
            } else {
                // The user document does not exist or has no data
                println("Profiles collection does not exist")
                trySend(listOf<ProfileData>()) // send default object
            }
        }
        awaitClose { subscription.remove() }
    }

}