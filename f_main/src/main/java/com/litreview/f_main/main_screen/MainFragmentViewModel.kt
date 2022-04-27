package com.litreview.f_main.main_screen

import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel

class MainFragmentViewModel: MviStatefulViewModel<MainFragmentState, MainFragmentEvent>() {

    override val reducer: Reducer<MainFragmentEvent, MainFragmentState> = MainFragmentReducer()
    override val state: FlowState<MainFragmentState> = FlowState(MainFragmentState())
    override val middleware: DslFlowMiddleware<MainFragmentEvent> = MainFragmentMiddleware()

    init {
        bindFlow()
    }
}