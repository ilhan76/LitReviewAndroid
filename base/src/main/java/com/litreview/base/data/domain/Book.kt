package com.litreview.base.data.domain

import java.io.Serializable

data class Book(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String,
    val rate: Double,
    val author: Author?
) : Serializable {

    val authorName: String
        get() {
            return author?.firstName + /*if (!author?.firstName.isNullOrEmpty()) " " else ""*/ " " +
                    author?.middleName + /*if (!author?.middleName.isNullOrEmpty()) " " else ""*/ " " +
                    author?.lastName
        }

}
