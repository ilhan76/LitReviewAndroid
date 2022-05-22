package com.litreview.f_auth.auth

import com.litreview.base.util.Command
import com.litreview.base.util.EMPTY_STRING
import com.litreview.base.validation.ValidationFieldType
import com.litreview.base.validation.ValidationResult
import ru.surfstudio.mvi.core.reducer.Reducer
import com.litreview.f_auth.auth.AuthFragmentEvent.*
import com.litreview.i_navigation.NavCommand
import javax.inject.Inject
import javax.inject.Singleton

data class AuthState(
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val emailValidationResult: ValidationResult? = null,
    val passwordValidationResult: ValidationResult? = null
)

@Singleton
class AuthCommandHolder @Inject constructor() {
    val openScreen = Command<NavCommand>()
    val showError = Command<String>()
}

class AuthFragmentReducer @Inject constructor() : Reducer<AuthFragmentEvent, AuthState> {

    override fun reduce(state: AuthState, event: AuthFragmentEvent): AuthState {
        return when (event) {
            is EmailChangedEvent -> onEmailChangedEvent(event, state)
            is PasswordChangedEvent -> onPasswordChangedEvent(event, state)
            is ValidationEvent.Result -> onValidationResult(event, state)
            else -> state
        }
    }

    private fun onEmailChangedEvent(
        event: EmailChangedEvent,
        state: AuthState
    ): AuthState {
        return state.copy(
            email = event.email
        )
    }

    private fun onPasswordChangedEvent(
        event: PasswordChangedEvent,
        state: AuthState
    ): AuthState {
        return state.copy(
            password = event.password
        )
    }

    private fun onValidationResult(
        event: ValidationEvent.Result,
        state: AuthState
    ): AuthState {
        var emailValidationResult = state.emailValidationResult
        var passwordValidationResult = state.passwordValidationResult

        event.resultList.forEach { result ->
            when (result.fieldType) {
                ValidationFieldType.EMAIL -> emailValidationResult = result
                ValidationFieldType.PASSWORD -> passwordValidationResult = result
                else -> {
                    // do nothing
                }
            }
        }
        return state.copy(
            emailValidationResult = emailValidationResult,
            passwordValidationResult = passwordValidationResult
        )
    }
}