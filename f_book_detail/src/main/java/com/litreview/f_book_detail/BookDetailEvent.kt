package com.litreview.f_book_detail

import com.litreview.base.data.domain.Book
import ru.surfstudio.mvi.core.event.Event

sealed class BookDetailEvent : Event {

    object OnCreateEvent : BookDetailEvent()

    object OpenWriteReviewScreen : BookDetailEvent()

    object OpenReviewsScreen : BookDetailEvent()

    object BookmarkClickEvent : BookDetailEvent()

    data class UpdateIsAddedToBookmarksStatus(
        val isAdded: Boolean
    ) : BookDetailEvent()

    data class UpdateAuthStatus(
        val isAuthorized: Boolean
    ) : BookDetailEvent()

    data class UpdateBookEvent(
        val book: Book
    ) : BookDetailEvent()
}