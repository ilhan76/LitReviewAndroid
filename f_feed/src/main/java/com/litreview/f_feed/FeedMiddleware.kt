package com.litreview.f_feed

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class FeedMiddleware @Inject constructor() : DslFlowMiddleware<FeedEvent> {

    override fun transform(eventStream: Flow<FeedEvent>): Flow<FeedEvent> {
        return eventStream.transformations {
            addAll()
        }
    }
}