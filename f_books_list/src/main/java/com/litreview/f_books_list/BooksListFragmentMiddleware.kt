package com.litreview.f_books_list

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class BooksListFragmentMiddleware @Inject constructor(): DslFlowMiddleware<BooksListFragmentEvent> {

    override fun transform(eventStream: Flow<BooksListFragmentEvent>): Flow<BooksListFragmentEvent> {
        return eventStream.transformations {
            addAll()
        }
    }
}