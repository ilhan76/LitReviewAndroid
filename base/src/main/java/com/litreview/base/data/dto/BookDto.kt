package com.litreview.base.data.dto

import com.google.gson.annotations.SerializedName
import com.litreview.base.data.Transformable
import com.litreview.base.data.domain.Book

data class BookDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("author")
    val author: AuthorDto?
) : Transformable<Book> {
    override fun transform(): Book {
        return Book(
            id = id,
            title = title,
            imageUrl = imageUrl,
            description = description,
            rate = rate,
            author = author?.transform()
        )
    }

}