package com.novakovic.tin.nutmegtest.model

import com.novakovic.tin.nutmegtest.ui.MainActivityModel


data class UserPostModel(
        val userId: Int,
        val id: Int,
        val title: String,
        val body: String,
        val username: String
)

fun UserPostModel.mapToMainViewUserModel() = MainActivityModel(title, username)

