package com.litreview.base.data.domain

data class Review(
    val id: String,
    val date: String,
    val text: String,
    val rate: Double,
    val book: Book,
    val userInfo: PublicUser? = null
)