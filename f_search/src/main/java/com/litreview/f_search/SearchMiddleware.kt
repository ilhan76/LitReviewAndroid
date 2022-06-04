package com.litreview.f_search

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class SearchMiddleware @Inject constructor() : DslFlowMiddleware<SearchEvent> {
    override fun transform(eventStream: Flow<SearchEvent>): Flow<SearchEvent> {
        return eventStream
    }
}