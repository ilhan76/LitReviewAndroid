package com.litreview.f_auth

import com.litreview.base.util.EMPTY_STRING
import ru.surfstudio.mvi.core.reducer.Reducer
import com.litreview.f_auth.AuthFragmentEvent.*
import javax.inject.Inject

data class AuthState (
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING
)

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