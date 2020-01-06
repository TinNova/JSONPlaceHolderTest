package com.novakovic.tin.nutmegtest.di.component

import android.app.Application
import com.novakovic.tin.nutmegtest.NutmegTestApplication
import com.novakovic.tin.nutmegtest.di.module.ActivityModule
import com.novakovic.tin.nutmegtest.di.module.ApiModule
import com.novakovic.tin.nutmegtest.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    ApiModule::class
])

interface AppComponent : AndroidInjector<NutmegTestApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}