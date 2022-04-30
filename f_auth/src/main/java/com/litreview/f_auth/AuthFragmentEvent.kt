package com.litreview.f_auth

import androidx.navigation.NavController
import ru.surfstudio.mvi.core.event.Event

sealed class AuthFragmentEvent : Event {

    data class BackPressed(
        val navController: NavController?
    ) : AuthFragmentEvent()

    data class EmailChangedEvent(
        val email: String
    ): AuthFragmentEvent()

    data class PasswordChangedEvent(
        val password: String
    ): AuthFragmentEvent()

    data class LoginClickedEvent(
        val navController: NavController?
    ) : AuthFragmentEvent()
}