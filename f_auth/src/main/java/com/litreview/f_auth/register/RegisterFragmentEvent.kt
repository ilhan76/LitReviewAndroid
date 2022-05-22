package com.litreview.f_auth.register

import ru.surfstudio.mvi.core.event.Event

sealed class RegisterFragmentEvent : Event {

    data class NameChangedEvent(val value: String) : RegisterFragmentEvent()
    data class SecondNameChangedEvent(val value: String) : RegisterFragmentEvent()
    data class EmailChangedEvent(val value: String) : RegisterFragmentEvent()
    data class PasswordChangedEvent(val value: String) : RegisterFragmentEvent()
    data class PhoneChangedEvent(val value: String) : RegisterFragmentEvent()

}