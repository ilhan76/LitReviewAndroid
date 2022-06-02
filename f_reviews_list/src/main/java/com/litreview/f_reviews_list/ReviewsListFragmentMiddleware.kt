package com.litreview.f_reviews_list

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware

class ReviewsListFragmentMiddleware : DslFlowMiddleware<ReviewsListFragmentEvent> {
    override fun transform(eventStream: Flow<ReviewsListFragmentEvent>): Flow<ReviewsListFragmentEvent> {
        return eventStream.transformations {
            addAll()
        }
    }
}