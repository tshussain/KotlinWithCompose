package com.example.kotlinwithcompose

import android.app.Application

/** This file allows us to provide a single ("static") module that can be accessed
 *   everywhere in the code, and in turn provide the specific (singleton) objects we will inject.
 */
class MyApp: Application() {

    /* Always be able to access the module ("static") */
    companion object {
        lateinit var appModule: AppModule
    }

    /* Called only once at beginning of application's lifetime */
    override fun onCreate() {
        super.onCreate()
        appModule = AppModule(this)
    }
}