package com.novakovic.tin.nutmegtest.ui

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.novakovic.tin.nutmegtest.model.UserPostModel
import com.novakovic.tin.nutmegtest.model.mapToMainViewUserModel
import com.novakovic.tin.nutmegtest.repo.ContentRepo
import com.novakovic.tin.nutmegtest.ui.base.DisposingViewModel
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val contentRepo: ContentRepo,
        application: Application) : DisposingViewModel(application) {

    val viewState = MutableLiveData<MainViewState>()

    init {
        viewState.value = MainViewState(listOf())
    }

    fun initView() {
        getSanatisedPosts()
    }

    fun getSanatisedPosts() {
        contentRepo.getSanatisedPosts().subscribeBy(
                onSuccess = {
                    viewState.value = viewState.value?.copy(
                            listData = it.map {
                                it.mapToMainViewUserModel()
                            },
                            isLoading = false,
                            isDataReady = true
                    )
                },
                onError = {
                    viewState.value = viewState.value?.copy(
                            isLoading = false,
                            isDataReady = false
                    )
                })
    }
}
