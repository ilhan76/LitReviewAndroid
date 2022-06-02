package com.litreview.base.data.domain

data class Book(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String,
    val rate: Double,
    val author: Author?
)
