package com.litreview.f_main

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware

class MainFlowMiddleware: DslFlowMiddleware<MainFlowEvent> {

    override fun transform(eventStream: Flow<MainFlowEvent>): Flow<MainFlowEvent> {
        return eventStream.transformations {
            addAll()
        }
    }
}