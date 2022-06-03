package com.litreview.f_feed

import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.UserInfo
import ru.surfstudio.mvi.core.event.Event

sealed class FeedEvent : Event {

    object OnCreateEvent : FeedEvent()

    object LoadMyBooks : FeedEvent()
    object LoadNewBook : FeedEvent()
    object LoadBestBook : FeedEvent()

    data class UpdateProfile(
        val userInfo: UserInfo
    ) : FeedEvent()

    data class UpdateMyBooks(
        val books: List<Book>
    ) : FeedEvent()

    data class UpdateNewBooks(
        val books: List<Book>
    ) : FeedEvent()

    data class UpdateBestBooks(
        val books: List<Book>
    ) : FeedEvent()

    object SearchViewClickEvent : FeedEvent()

    data class OpenBookDetails(
        val book: Book?
    ) : FeedEvent()
}