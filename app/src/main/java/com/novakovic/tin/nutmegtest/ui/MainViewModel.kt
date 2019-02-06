package com.novakovic.tin.nutmegtest.ui

import android.app.Application
import com.novakovic.tin.nutmegtest.model.UserPostModel
import com.novakovic.tin.nutmegtest.repo.ContentRepo
import com.novakovic.tin.nutmegtest.ui.base.DisposingViewModel
import io.reactivex.Single
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MainViewModel(application: Application) : DisposingViewModel(application), KoinComponent {
    // Or inject contentRepo simply by passing it in the constructor, both work equally
    private val contentRepo: ContentRepo by inject()

    fun getSanatisedPosts(): Single<MutableList<UserPostModel>> = contentRepo.getSanatisedPosts()
}
