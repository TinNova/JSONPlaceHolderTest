package com.novakovic.tin.nutmegtest.network

import com.novakovic.tin.nutmegtest.model.PostModel
import com.novakovic.tin.nutmegtest.model.UserModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiMethods {

    @GET("posts")
    fun getPosts(): Single<List<PostModel>>

    @GET("users/{userId}")
    fun getUsers(@Path("userId") userId: Int): Single<UserModel>
}