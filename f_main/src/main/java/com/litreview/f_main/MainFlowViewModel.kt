package com.litreview.f_main

import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel

class MainFlowViewModel: MviStatefulViewModel<MainFlowState, MainFlowEvent>() {

    override val reducer: Reducer<MainFlowEvent, MainFlowState> = MainFlowReducer()
    override val state: FlowState<MainFlowState> = FlowState(MainFlowState())
    override val middleware: DslFlowMiddleware<MainFlowEvent> = MainFlowMiddleware()
}