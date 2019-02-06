package com.novakovic.tin.nutmegtest.di

import com.novakovic.tin.nutmegtest.NutmegTestApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (ActivityModule::class),
    (AppModule::class)
//    (FragmentModule::class)
])

interface AppComponent : AndroidInjector<NutmegTestApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NutmegTestApplication>()
}
