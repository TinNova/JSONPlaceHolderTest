package com.novakovic.tin.nutmegtest

import com.novakovic.tin.nutmegtest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class NutmegTestApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<NutmegTestApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}
