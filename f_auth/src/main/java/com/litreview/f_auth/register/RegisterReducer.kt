package com.litreview.f_auth.register

import ru.surfstudio.mvi.core.reducer.Reducer

data class AuthState(
    val email: String = "",
    val password: String = ""
)

class AuthReducer: Reducer<RegisterEvent, AuthState> {

    override fun reduce(state: AuthState, event: RegisterEvent): AuthState {
        TODO("Not yet implemented")
    }
}