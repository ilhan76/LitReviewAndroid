package com.litreview.f_start.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : MviStatefulViewModel<SplashState, SplashEvent>() {

    @Inject
    override lateinit var reducer: Reducer<SplashEvent, SplashState>

    @Inject
    override lateinit var state: FlowState<SplashState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<SplashEvent>
}