package com.novakovic.tin.nutmegtest

import com.novakovic.tin.nutmegtest.di.component.AppComponent
import com.novakovic.tin.nutmegtest.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NutmegTestApplication : DaggerApplication() {

    override fun applicationInjector():
            AndroidInjector<out DaggerApplication> {

        //Build app component
        val appComponent: AppComponent = DaggerAppComponent.builder()
                .application(this)
                .build()

        //inject application instance
        appComponent.inject(this)
        return appComponent
    }

}