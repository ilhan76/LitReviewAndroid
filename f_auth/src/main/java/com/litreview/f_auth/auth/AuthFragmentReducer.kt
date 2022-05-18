package com.litreview.f_auth.auth

import com.litreview.base.util.Command
import com.litreview.base.util.EMPTY_STRING
import ru.surfstudio.mvi.core.reducer.Reducer
import com.litreview.f_auth.auth.AuthFragmentEvent.*
import com.litreview.i_navigation.NavCommand
import javax.inject.Inject
import javax.inject.Singleton

data class AuthState(
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING
)

@Singleton
class AuthCommandHolder @Inject constructor() {
    val openScreen = Command<NavCommand>()
    val showEmailValidationError = Command<Int>()
    val showPasswordValidationError = Command<Int>()
}

class AuthFragmentReducer @Inject constructor() : Reducer<AuthFragmentEvent, AuthState> {

    override fun reduce(state: AuthState, event: AuthFragmentEvent): AuthState {
        return when (event) {
            is EmailChangedEvent -> onEmailChangedEvent(event, state)
            is PasswordChangedEvent -> onPasswordChangedEvent(event, state)
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
}