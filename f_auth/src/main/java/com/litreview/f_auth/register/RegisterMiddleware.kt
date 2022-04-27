package com.litreview.f_auth.register

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware

class RegisterMiddleware: DslFlowMiddleware<RegisterEvent> {

    override fun transform(eventStream: Flow<RegisterEvent>): Flow<RegisterEvent> {
        TODO("Not yet implemented")
    }
}