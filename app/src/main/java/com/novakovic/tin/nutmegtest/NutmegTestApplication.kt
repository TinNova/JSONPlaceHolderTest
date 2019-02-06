package com.novakovic.tin.nutmegtest

import android.app.Application
import com.novakovic.tin.nutmegtest.koin.applicationModule
import org.koin.android.ext.android.startKoin

/**
 * Application class is used to start Koin on onCreate.
 */
class NutmegTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule))
    }
}
