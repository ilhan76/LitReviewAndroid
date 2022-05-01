package com.litreview.f_start.main_screen

import androidx.navigation.NavController
import ru.surfstudio.mvi.core.event.Event

sealed class MainFragmentEvent : Event {

    data class SignInClicked(
        val navController: NavController?
    ) : MainFragmentEvent()

    data class SignUpClicked(
        val navController: NavController?
    ) : MainFragmentEvent()
}
