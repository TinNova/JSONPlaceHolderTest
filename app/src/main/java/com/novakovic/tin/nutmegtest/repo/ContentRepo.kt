package com.novakovic.tin.nutmegtest.repo

import com.novakovic.tin.nutmegtest.model.PostModel
import com.novakovic.tin.nutmegtest.model.UserModel
import com.novakovic.tin.nutmegtest.network.ApiMethods
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContentRepo(private val apiMethods: ApiMethods) {

    fun getPosts(): Single<List<PostModel>> = apiMethods.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getUsers(userId: Int): Single<UserModel> = apiMethods.getUsers(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
