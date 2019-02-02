package com.novakovic.tin.nutmegtest

import android.app.Application
import com.novakovic.tin.nutmegtest.network.ApiMethods
import com.novakovic.tin.nutmegtest.network.ServiceProvider
import com.novakovic.tin.nutmegtest.repo.ContentRepo

/**
 * Application class is used as a basic dependency manager, should be improved with Dagger/Koin.
 */
class NutmegTest : Application() {

    private val apiMethods: ApiMethods by lazy { ServiceProvider.buildJsonPlaceHolderApi() }
    internal val contentRepo: ContentRepo by lazy { ContentRepo(apiMethods) }

}