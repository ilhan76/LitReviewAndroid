package com.litreview.f_auth.register

import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel

class RegisterViewModel: MviStatefulViewModel<AuthState, RegisterEvent>() {

    override val state: FlowState<AuthState> = FlowState(AuthState())
    override val middleware: DslFlowMiddleware<RegisterEvent> = RegisterMiddleware()
    override val reducer: Reducer<RegisterEvent, AuthState> = AuthReducer()

    init {
        bindFlow()
    }
}