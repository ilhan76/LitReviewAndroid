package com.litreview.f_start.main_screen

import ru.surfstudio.mvi.core.event.Event

sealed class MainFragmentEvent : Event {

    object SignInClicked: MainFragmentEvent()

    object SignUpClicked : MainFragmentEvent()

    object ContinueAsGuest : MainFragmentEvent()
}
