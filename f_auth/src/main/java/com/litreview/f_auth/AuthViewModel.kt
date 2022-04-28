package com.litreview.f_auth

import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel

class AuthViewModel: MviStatefulViewModel<AuthState, AuthEvent>() {

    override val state: FlowState<AuthState> = FlowState(AuthState())
    override val middleware: DslFlowMiddleware<AuthEvent> = AuthMiddleware()
    override val reducer: Reducer<AuthEvent, AuthState> = AuthReducer()

}