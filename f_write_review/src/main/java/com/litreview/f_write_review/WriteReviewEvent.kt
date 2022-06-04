package com.litreview.f_write_review

import com.litreview.base.data.domain.Book
import ru.surfstudio.mvi.core.event.Event

sealed class WriteReviewEvent : Event {
    data class SendReview(
        val book: Book,
        val text: String,
        val rating: Int
    ) : WriteReviewEvent()
}