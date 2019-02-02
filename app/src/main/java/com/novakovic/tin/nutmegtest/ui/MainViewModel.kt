package com.novakovic.tin.nutmegtest.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.novakovic.tin.nutmegtest.NutmegTest
import com.novakovic.tin.nutmegtest.model.PostModel
import com.novakovic.tin.nutmegtest.model.UserModel
import com.novakovic.tin.nutmegtest.repo.ContentRepo
import io.reactivex.Single

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val contentRepo: ContentRepo = (application as NutmegTest).contentRepo

    fun getPosts(): Single<List<PostModel>> = contentRepo.getPosts()

    fun getUsers(userId: Int): Single<UserModel> = contentRepo.getUsers(userId)

}