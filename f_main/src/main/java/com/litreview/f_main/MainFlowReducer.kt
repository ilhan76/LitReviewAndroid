package com.litreview.f_main

import com.litreview.f_main.MainFlowEvent
import ru.surfstudio.mvi.core.reducer.Reducer

class MainFlowState

class MainFlowReducer : Reducer<MainFlowEvent, MainFlowState> {

    override fun reduce(state: MainFlowState, event: MainFlowEvent): MainFlowState {
        return when (event) {
            else -> state
        }
    }
}