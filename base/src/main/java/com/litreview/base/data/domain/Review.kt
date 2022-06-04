package com.litreview.base.data.domain

import java.io.Serializable

data class Review(
    val id: String,
    val date: String,
    val text: String,
    val rate: Double,
    val book: Book,
    val userInfo: PublicUser? = null
) : Serializable {
    val userName: String
        get() {
            return userInfo?.firstName + if (userInfo?.firstName.isNullOrEmpty()) " " else "" +
                    userInfo?.secondName
        }
}