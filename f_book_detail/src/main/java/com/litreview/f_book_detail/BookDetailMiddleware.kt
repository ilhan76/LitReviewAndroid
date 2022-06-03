package com.litreview.f_book_detail

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class BookDetailMiddleware @Inject constructor() : DslFlowMiddleware<BookDetailEvent> {
    override fun transform(eventStream: Flow<BookDetailEvent>): Flow<BookDetailEvent> {
        return eventStream
    }
}