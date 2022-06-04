package com.litreview.base.data.domain

data class PublicUser(
    val id: String,
    val firstName: String,
    val secondName: String,
    val avatarUrl: String,
    val description: String?,
    val rate: Double?
)