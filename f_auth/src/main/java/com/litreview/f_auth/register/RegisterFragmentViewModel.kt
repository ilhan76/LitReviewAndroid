package com.litreview.f_auth.register

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor() :
    MviStatefulViewModel<RegisterState, RegisterFragmentEvent>() {

    @Inject
    override lateinit var state: FlowState<RegisterState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<RegisterFragmentEvent>

    @Inject
    override lateinit var reducer: RegisterFragmentReducer
}