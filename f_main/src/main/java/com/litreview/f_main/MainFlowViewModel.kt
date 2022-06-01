package com.litreview.f_main

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class MainFlowViewModel @Inject constructor() :
    MviStatefulViewModel<MainFlowState, MainFlowEvent>() {

    @Inject
    override lateinit var reducer: Reducer<MainFlowEvent, MainFlowState>

    @Inject
    override lateinit var state: FlowState<MainFlowState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<MainFlowEvent>
}