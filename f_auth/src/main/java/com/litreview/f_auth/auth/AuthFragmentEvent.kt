package com.litreview.f_auth.auth

import ru.surfstudio.mvi.core.event.Event

sealed class AuthFragmentEvent : Event {

    object BackPressed: AuthFragmentEvent()
    object LoginClickedEvent : AuthFragmentEvent()

    data class EmailChangedEvent(
        val email: String
    ): AuthFragmentEvent()

    data class PasswordChangedEvent(
        val password: String
    ): AuthFragmentEvent()

    data class Validation(
        val email: String,
        val password: String
    ) : AuthFragmentEvent()
}