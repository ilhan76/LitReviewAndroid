package com.litreview.base.data.domain

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