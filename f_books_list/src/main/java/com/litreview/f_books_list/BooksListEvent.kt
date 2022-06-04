package com.litreview.f_books_list

import com.litreview.base.data.domain.Book
import ru.surfstudio.mvi.core.event.Event

sealed class BooksListEvent: Event {

    data class OpenBookDetailsScreen(
        val book: Book
    ) : BooksListEvent()
}