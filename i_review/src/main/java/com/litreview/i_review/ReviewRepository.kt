package com.litreview.i_review

import com.litreview.base.data.domain.Review
import com.litreview.i_review.data.CreateReviewRequest
import com.litreview.i_network.responseCheck
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewApi: ReviewApi
) {

    suspend fun getReviewsByBook(
        title: String
    ): List<Review> {
        return reviewApi.getReviewsByBook(title).responseCheck().body()!!.map {
            it.transform()
        }
    }

    suspend fun sendReview(
        bookId: String,
        text: String,
        rate: Int
    ) {
        reviewApi.sendReview(
            CreateReviewRequest(bookId, text, rate)
        ).responseCheck()
    }
}