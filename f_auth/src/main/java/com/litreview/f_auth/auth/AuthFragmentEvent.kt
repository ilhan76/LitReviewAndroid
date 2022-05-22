package com.litreview.f_auth.auth

import com.litreview.base.validation.ValidationRequest
import com.litreview.base.validation.ValidationResult
import com.litreview.base.validation.ValidationResultEvent
import ru.surfstudio.mvi.core.event.Event

sealed class AuthFragmentEvent : Event {

    object LoginClickedEvent : AuthFragmentEvent()

    data class EmailChangedEvent(
        val email: String
    ) : AuthFragmentEvent()

    data class PasswordChangedEvent(
        val password: String
    ) : AuthFragmentEvent()

    sealed class ValidationEvent : AuthFragmentEvent() {

        data class Request(
            val requests: List<ValidationRequest>
        ) : ValidationEvent()

        data class Result(
            override val resultList: List<ValidationResult>
        ) : ValidationEvent(), ValidationResultEvent
    }
}