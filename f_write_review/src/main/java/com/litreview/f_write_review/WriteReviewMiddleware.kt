package com.litreview.f_write_review

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class WriteReviewMiddleware @Inject constructor() : DslFlowMiddleware<WriteReviewEvent> {
    override fun transform(eventStream: Flow<WriteReviewEvent>): Flow<WriteReviewEvent> {
        return eventStream
    }
}