package com.litreview.f_auth.register

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class RegisterFragmentMiddleware @Inject constructor() : DslFlowMiddleware<RegisterFragmentEvent> {

    override fun transform(eventStream: Flow<RegisterFragmentEvent>): Flow<RegisterFragmentEvent> {
        TODO("Not yet implemented")
    }
}