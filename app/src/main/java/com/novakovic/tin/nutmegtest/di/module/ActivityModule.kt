package com.novakovic.tin.nutmegtest.di.module

import com.novakovic.tin.nutmegtest.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}