package com.litreview.f_books_list

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class BooksListMiddleware @Inject constructor(): DslFlowMiddleware<BooksListEvent> {

    override fun transform(eventStream: Flow<BooksListEvent>): Flow<BooksListEvent> {
        return eventStream.transformations {
            addAll()
        }
    }
}