package com.litreview.i_review.data

import com.google.gson.annotations.SerializedName

data class CreateReviewRequest(
    @SerializedName("bookId")
    val bookId: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("rate")
    val rate: Int
)