package com.litreview.f_auth.register

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class RegisterFragmentMiddleware @Inject constructor() : DslFlowMiddleware<RegisterFragmentEvent> {

    override fun transform(eventStream: Flow<RegisterFragmentEvent>): Flow<RegisterFragmentEvent> {
        return eventStream.transformations {
            addAll()
        }
    }
}