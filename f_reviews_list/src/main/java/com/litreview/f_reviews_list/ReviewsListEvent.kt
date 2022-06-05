package com.litreview.f_reviews_list

import com.litreview.base.data.domain.Review
import ru.surfstudio.mvi.core.event.Event

sealed class ReviewsListEvent : Event {
    object GetReviewFromBuffer : ReviewsListEvent()

    data class UpdateReviewsList(
        val list: List<Review>
    ) : ReviewsListEvent()
}