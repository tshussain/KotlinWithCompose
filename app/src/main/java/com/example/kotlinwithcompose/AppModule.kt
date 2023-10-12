package com.example.kotlinwithcompose

import android.content.Context
import com.example.kotlinwithcompose.model.ProfileRepository
import com.example.kotlinwithcompose.model.ProfileRepositoryDataStore

/** This module provides the specific object(s) we will inject */
class AppModule(
    private val appContext: Context
) {
    /* Create appropriate repository (backed by a DataStore) on first use.
       Only one copy will be created during lifetime of the application. */
    val profileRepository : ProfileRepository by lazy {
        ProfileRepositoryDataStore(appContext)
    }
}