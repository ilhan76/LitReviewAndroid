package com.litreview.f_book_detail

import ru.surfstudio.mvi.core.event.Event

sealed class BookDetailEvent : Event {

    data class CheckIsBookAdded(
        val id: Int
    ) : BookDetailEvent()

    data class BookmarkClickEvent(
        val id: String
    ) : BookDetailEvent()

    data class UpdateIsAddedToBookmarksStatus(
        val isAdded: Boolean
    ) : BookDetailEvent()
}