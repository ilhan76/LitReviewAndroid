package com.litreview.f_auth

import com.litreview.i_navigation.open
import com.litreview.i_navigation.providers.AuthNavCommandProvider
import com.litreview.f_auth.AuthEvent.*
import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware

class AuthMiddleware(
    private val authNavCommandProvider: AuthNavCommandProvider
): DslFlowMiddleware<AuthEvent> {

    override fun transform(eventStream: Flow<AuthEvent>): Flow<AuthEvent> {
        return eventStream.transformations {
            addAll(
                BackPressed::class react ::handleBackPressed,
                LoginClickedEvent::class react ::handleLoginClickedEvent
            )
        }
    }

    private fun handleLoginClickedEvent(event: LoginClickedEvent) {
        event.navController?.open(authNavCommandProvider.toFeed)
    }

    private fun handleBackPressed(event: BackPressed) {
        event.navController?.open(authNavCommandProvider.toMain)
    }
}