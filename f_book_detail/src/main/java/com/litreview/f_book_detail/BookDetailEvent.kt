package com.litreview.f_book_detail

import com.litreview.base.data.domain.Book
import ru.surfstudio.mvi.core.event.Event

sealed class BookDetailEvent : Event {

    data class OpenWriteReviewScreen(
        val book: Book
    ) : BookDetailEvent()

    data class OpenReviewsScreen(
        val book: Book
    ) : BookDetailEvent()

    data class CheckIsBookAdded(
        val id: Int
    ) : BookDetailEvent()

    data class BookmarkClickEvent(
        val book: Book
    ) : BookDetailEvent()

    data class UpdateIsAddedToBookmarksStatus(
        val isAdded: Boolean
    ) : BookDetailEvent()
}