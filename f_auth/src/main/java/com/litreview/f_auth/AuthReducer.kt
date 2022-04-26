package com.litreview.f_auth

import ru.surfstudio.mvi.core.reducer.Reducer

data class AuthState(
    val email: String = "",
    val password: String = ""
)

class AuthReducer: Reducer<AuthEvent, AuthState> {

    override fun reduce(state: AuthState, event: AuthEvent): AuthState {
        TODO("Not yet implemented")
    }
}