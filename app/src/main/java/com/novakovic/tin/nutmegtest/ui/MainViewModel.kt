package com.novakovic.tin.nutmegtest.ui

import com.novakovic.tin.nutmegtest.NutmegTestApplication
import com.novakovic.tin.nutmegtest.model.UserPostModel
import com.novakovic.tin.nutmegtest.repo.ContentRepo
import com.novakovic.tin.nutmegtest.ui.base.DisposingViewModel
import io.reactivex.Single
import javax.inject.Inject

class MainViewModel @Inject constructor(private val contentRepo: ContentRepo,
        application: NutmegTestApplication) : DisposingViewModel(application) {

    fun getSanatisedPosts(): Single<MutableList<UserPostModel>> = contentRepo.getSanatisedPosts()

}
