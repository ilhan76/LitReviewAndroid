package com.litreview.i_review.data

import com.google.gson.annotations.SerializedName
import com.litreview.base.data.Transformable
import com.litreview.base.data.domain.Review
import com.litreview.base.data.dto.BookDto
import com.litreview.base.data.dto.PublicUserDto

data class ReviewDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("createdAt")
    val date: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("user")
    val user: PublicUserDto,
    @SerializedName("book")
    val book: BookDto
) : Transformable<Review> {
    override fun transform(): Review {
        return Review(
            id = id,
            date = date, //todo - добавить форматирование
            text = text,
            rate = rate,
            book = book.transform(),
            userInfo = user.transform()
        )
    }
}