package com.litreview.base.data.domain

data class Review(
    val text: String,
    val rate: Double,
    val book: Book,
    val userInfo: PublicUserInfo? = null
)