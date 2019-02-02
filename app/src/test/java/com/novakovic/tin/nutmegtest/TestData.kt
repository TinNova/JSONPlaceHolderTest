package com.novakovic.tin.nutmegtest

import com.novakovic.tin.nutmegtest.model.PostModel
import com.novakovic.tin.nutmegtest.model.UserModel
import com.novakovic.tin.nutmegtest.model.UserPostModel

internal val testApiPostResponse = arrayListOf(
        PostModel(
                userId = 1,
                id = 1,
                title = "TitleOne",
                body = "BodyOne"
        ),
        PostModel(
                userId = 2,
                id = 2,
                title = "TitleTwo",
                body = "BodyTwo"
        ),
        PostModel(
                userId = 3,
                id = 3,
                title = "TitleThree",
                body = "BodyThree"
        )
)

internal val testApiUserResponse = arrayListOf(
        UserModel(
                id = 1,
                username = "Goran"
        ),
        UserModel(
                id = 2,
                username = "Noemi"
        ),
        UserModel(
                id = 3,
                username = "Tin"
        )
)

internal val testSanatisedPostResponse = mutableListOf(
        UserPostModel(
                userId = 1,
                id = 1,
                title = "TitleOne",
                body = "BodyOne",
                username = "Goran"
        ),
        UserPostModel(
                userId = 2,
                id = 2,
                title = "TitleTwo",
                body = "BodyTwo",
                username = "Noemi"
        ),
        UserPostModel(
                userId = 3,
                id = 3,
                title = "TitleThree",
                body = "BodyThree",
                username = "Tin"
        )
)
