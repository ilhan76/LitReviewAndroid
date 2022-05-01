package com.litreview.f_auth

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class AuthFragmentViewModel @Inject constructor() : MviStatefulViewModel<AuthState, AuthFragmentEvent>() {

    override val state: FlowState<AuthState> = FlowState(AuthState())

    @Inject
    override lateinit var middleware: DslFlowMiddleware<AuthFragmentEvent>

    @Inject
    override lateinit var reducer: AuthFragmentReducer

}