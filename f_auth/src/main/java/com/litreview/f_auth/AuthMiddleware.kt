package com.litreview.f_auth

import com.litreview.base.navigation.providers.MainNavCommandProvider
import com.litreview.base.navigation.providers.impl.MainNavCommandProviderImpl
import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware

class AuthMiddleware(
    val mainNavCommandProvider: MainNavCommandProvider = MainNavCommandProviderImpl()
): DslFlowMiddleware<AuthEvent> {

    override fun transform(eventStream: Flow<AuthEvent>): Flow<AuthEvent> {
        TODO("Not yet implemented")
    }
}