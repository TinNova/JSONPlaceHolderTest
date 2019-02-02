package com.novakovic.tin.nutmegtest.repo

import com.novakovic.tin.nutmegtest.model.PostModel
import com.novakovic.tin.nutmegtest.model.UserModel
import com.novakovic.tin.nutmegtest.model.UserPostModel
import com.novakovic.tin.nutmegtest.network.ApiMethods
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class ContentRepo(private val apiMethods: ApiMethods) {

    private fun getPosts(): Single<List<PostModel>> = apiMethods.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getUsers(): Single<List<UserModel>> = apiMethods.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getUser(userId: Int): Single<UserModel> = apiMethods.getUser(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getSanatisedPosts(): Single<MutableList<UserPostModel>> = Single.zip(
            getPosts(),
            getUsers(),
            BiFunction<List<PostModel>, List<UserModel>, MutableList<UserPostModel>> { posts, users ->

                val userPosts: MutableList<UserPostModel> = mutableListOf()

                posts.forEach { post ->
                    val userId = post.userId

                    users.forEach { user ->
                        if (userId == user.id) {
                            val userPostModel = UserPostModel(
                                    userId = userId,
                                    id = post.id,
                                    title = post.title,
                                    body = post.body,
                                    username = user.username)

                            userPosts.add(userPostModel)
                        }
                    }
                }
                return@BiFunction userPosts
            })
}
