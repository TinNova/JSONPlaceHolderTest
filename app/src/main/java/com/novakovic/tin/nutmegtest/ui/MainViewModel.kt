package com.novakovic.tin.nutmegtest.ui

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.novakovic.tin.nutmegtest.model.mapToMainViewUserModel
import com.novakovic.tin.nutmegtest.repo.ContentRepo
import com.novakovic.tin.nutmegtest.ui.base.DisposingViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val contentRepo: ContentRepo,
        application: Application) : DisposingViewModel(application) {

    val viewState = MutableLiveData<MainViewState>()

    // Forcing a default value into the viewState through an init block
    init {
        viewState.value = MainViewState()
    }

    fun initView() {
        getPosts()
    }

    fun getPosts() {
        add(contentRepo.getSanatisedPosts().subscribe(
                {
                    viewState.value = viewState.value?.copy(
                            listData = it.map { it.mapToMainViewUserModel() },
                            isLoading = false,
                            isDataReady = true,
                            isNetworkError = false
                    )
                },
                {
                    viewState.value = viewState.value?.copy(
                            isLoading = false,
                            isDataReady = false,
                            isNetworkError = true
                    )
                }
        ))
    }
}
