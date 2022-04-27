package com.litreview.f_auth

import androidx.navigation.NavController
import ru.surfstudio.mvi.core.event.Event

sealed class AuthEvent : Event {

    data class BackPressed(
        val navController: NavController?
    ): AuthEvent()
}