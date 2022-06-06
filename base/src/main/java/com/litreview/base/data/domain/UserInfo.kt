package com.litreview.base.data.domain

import com.litreview.base.util.EMPTY_STRING

data class UserInfo(
    val email: String,
    val firstName: String,
    val secondName: String,
    val phone: String,
    val description: String?,
    val avatarUrl: String?,
    val rate: Double?,
    val reviews: List<Review>,
    val books: List<Book>
)

fun UserInfo.toPublicUserInfo() : PublicUser {
    return PublicUser(
        id = EMPTY_STRING,
        firstName = firstName,
        secondName = secondName,
        avatarUrl = avatarUrl,
        description = description,
        rate = rate
    )
}