package com.litreview.f_start.main_screen

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor() :
    MviStatefulViewModel<MainFragmentState, MainFragmentEvent>() {

    override val state: FlowState<MainFragmentState> = FlowState(MainFragmentState())

    @Inject
    override lateinit var reducer: MainFragmentReducer

    @Inject
    override lateinit var middleware: DslFlowMiddleware<MainFragmentEvent>

}