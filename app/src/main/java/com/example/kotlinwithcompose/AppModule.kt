package com.example.kotlinwithcompose

import android.content.Context
import com.example.kotlinwithcompose.model.ProfileDataStore

/** This module provides the specific object(s) we will inject */
class AppModule(
    private val appContext: Context
) {
    /* Create appropriate dataStore on first use.
       Only one copy will be created during lifetime of the application. */
    val profileDataStore : ProfileDataStore by lazy {
        ProfileDataStore(appContext)
    }
}