package com.litreview.base.data.domain

import java.io.Serializable

data class Book(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String,
    val rate: Double,
    val author: Author?
) : Serializable
