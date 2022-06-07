package com.litreview.f_main

import ru.surfstudio.mvi.core.event.Event

sealed class MainFlowEvent : Event {

    object CheckAuthStatus : MainFlowEvent()

    data class UpdateAuthState(
        val isAuth: Boolean
    ) : MainFlowEvent()
}