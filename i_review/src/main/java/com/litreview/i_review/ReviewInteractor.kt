package com.litreview.i_review

import com.litreview.base.data.domain.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReviewInteractor @Inject constructor(
    private val reviewRepository: ReviewRepository
) {

    suspend fun getReviewsByBook(
        title: String
    ): List<Review> {
        return withContext(Dispatchers.IO) {
            reviewRepository.getReviewsByBook(title)
        }
    }

    suspend fun sendReview(
        bookId: String,
        text: String,
        rate: Int
    ) {
        withContext(Dispatchers.IO) {
            reviewRepository.sendReview(bookId, text, rate)
        }
    }
}