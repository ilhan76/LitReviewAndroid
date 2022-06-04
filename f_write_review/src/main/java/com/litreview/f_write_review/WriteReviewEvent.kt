package com.litreview.f_write_review

import com.litreview.base.data.domain.Book
import ru.surfstudio.mvi.core.event.Event

sealed class WriteReviewEvent : Event {

    data class ReviewTextChanged(
        val text: String
    ) : WriteReviewEvent()

    data class ReviewRateChanged(
        val rate: Int
    ) : WriteReviewEvent()

    data class SendReview(
        val book: Book
    ) : WriteReviewEvent()
}