package com.litreview.f_main

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class MainFlowMiddleware @Inject constructor() : DslFlowMiddleware<MainFlowEvent> {

    override fun transform(eventStream: Flow<MainFlowEvent>): Flow<MainFlowEvent> {
        return eventStream.transformations {
            addAll()
        }
    }
}