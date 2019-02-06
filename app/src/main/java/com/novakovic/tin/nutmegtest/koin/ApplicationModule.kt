package com.novakovic.tin.nutmegtest.koin


import com.novakovic.tin.nutmegtest.network.ServiceProvider
import com.novakovic.tin.nutmegtest.repo.ContentRepo
import com.novakovic.tin.nutmegtest.ui.MainViewModel
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

val applicationModule = module {

    // Setting up ViewModel
    viewModel<MainViewModel>()
    // Setting up ContentRepo
    single { ContentRepo(get()) }
    // Setting up OkHttp and Retrofit
    single { ServiceProvider.buildJsonPlaceHolderApi() }

}
