package com.litreview.f_auth.register

import com.litreview.base.validation.ValidationRequest
import com.litreview.base.validation.ValidationResult
import com.litreview.base.validation.ValidationResultEvent
import ru.surfstudio.mvi.core.event.Event

sealed class RegisterFragmentEvent : Event {

    data class NameChangedEvent(val value: String) : RegisterFragmentEvent()
    data class SecondNameChangedEvent(val value: String) : RegisterFragmentEvent()
    data class EmailChangedEvent(val value: String) : RegisterFragmentEvent()
    data class PasswordChangedEvent(val value: String) : RegisterFragmentEvent()
    data class PhoneChangedEvent(val value: String) : RegisterFragmentEvent()

    object RegisterBtnClickEvent : RegisterFragmentEvent()

    sealed class ValidationEvent : RegisterFragmentEvent() {

        data class Request(
            val requests: List<ValidationRequest>
        ) : RegisterFragmentEvent()

        data class Result(
            override val resultList: List<ValidationResult>
        ) : RegisterFragmentEvent(), ValidationResultEvent
    }

}