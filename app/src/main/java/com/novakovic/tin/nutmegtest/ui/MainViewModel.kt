package com.novakovic.tin.nutmegtest.ui

import android.app.Application
import com.novakovic.tin.nutmegtest.NutmegTest
import com.novakovic.tin.nutmegtest.model.UserPostModel
import com.novakovic.tin.nutmegtest.repo.ContentRepo
import com.novakovic.tin.nutmegtest.ui.base.DisposingViewModel
import io.reactivex.Single

class MainViewModel(application: Application) : DisposingViewModel(application) {

    private val contentRepo: ContentRepo = (application as NutmegTest).contentRepo

    fun getSanatisedPosts(): Single<MutableList<UserPostModel>> = contentRepo.getSanatisedPosts()

}
