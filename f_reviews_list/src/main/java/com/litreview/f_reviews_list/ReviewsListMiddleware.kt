package com.litreview.f_reviews_list

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class ReviewsListMiddleware @Inject constructor() : DslFlowMiddleware<ReviewsListEvent> {

    override fun transform(eventStream: Flow<ReviewsListEvent>): Flow<ReviewsListEvent> {
        return eventStream
    }
}