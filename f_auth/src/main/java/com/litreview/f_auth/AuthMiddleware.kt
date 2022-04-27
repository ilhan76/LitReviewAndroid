package com.litreview.f_auth

import com.litreview.base.navigation.open
import com.litreview.base.navigation.providers.AuthNavCommandProvider
import com.litreview.base.navigation.providers.impl.AuthNavCommandProviderImpl
import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware

class AuthMiddleware(
    private val authNavCommandProvider: AuthNavCommandProvider = AuthNavCommandProviderImpl()
): DslFlowMiddleware<AuthEvent> {

    override fun transform(eventStream: Flow<AuthEvent>): Flow<AuthEvent> {
        return eventStream.transformations {
            addAll(
                AuthEvent.BackPressed::class react ::handleBackPressed
            )
        }
    }

    private fun handleBackPressed(event: AuthEvent.BackPressed) {
        event.navController?.open(authNavCommandProvider.toMain)
    }
}