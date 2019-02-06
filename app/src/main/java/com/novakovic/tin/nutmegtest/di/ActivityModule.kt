package com.novakovic.tin.nutmegtest.di

import com.novakovic.tin.nutmegtest.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Collection of injectable Activities and the dependency modules they depend on.
 */
@Module
abstract class ActivityModule {

    @MainScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}
