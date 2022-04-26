package com.litreview.f_auth

import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware

class AuthMiddleware: DslFlowMiddleware<AuthEvent> {

    override fun transform(eventStream: Flow<AuthEvent>): Flow<AuthEvent> {
        TODO("Not yet implemented")
    }
}