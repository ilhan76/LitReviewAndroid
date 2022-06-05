package com.litreview.f_reviews_list

import com.litreview.base.storage.ReviewsBufferStorage
import com.litreview.f_reviews_list.ReviewsListEvent.*
import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class ReviewsListMiddleware @Inject constructor(
    private val reviewsBufferStorage: ReviewsBufferStorage
) : DslFlowMiddleware<ReviewsListEvent> {

    override fun transform(eventStream: Flow<ReviewsListEvent>): Flow<ReviewsListEvent> {
        return eventStream.transformations {
            addAll(
                GetReviewFromBuffer::class eventToEvent { getReviews() }
            )
        }
    }

    private fun getReviews(): ReviewsListEvent {
        val reviews = reviewsBufferStorage.get()
        return UpdateReviewsList(reviews)
    }
}