package com.example.kotlinwithcompose.model

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Preferences DataStore: It stores and accesses data using keys.
// This implementation does not require a predefined schema
//   and does not provide type safety.
const val PROFILE_DATASTORE ="profile_datastore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PROFILE_DATASTORE)
class ProfileDataStore (private val context: Context) {
    companion object {
        val NAME = stringPreferencesKey("NAME")
        val COUNTER = intPreferencesKey("COUNTER")
    }

    /** Update the values in the DataStore. */
    suspend fun saveToDataStore(profileData: ProfileData) {
        context.dataStore.edit {
            it[NAME] = profileData.name
            it[COUNTER] = profileData.counter
        }
    }

    /** Get the data in the DataStore as a flow.  Since the store may have never
     *     been used yet, handle the null case with default values. */
    fun getFromDataStore(): Flow<ProfileData> = context.dataStore.data.map {
        ProfileData(
            name = it[NAME] ?: "",
            counter = it[COUNTER] ?: 0
        )
    }

    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }

}