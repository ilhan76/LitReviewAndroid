package com.litreview.f_main

import com.litreview.base.util.Command
import com.litreview.i_navigation.NavCommand
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

class MainFlowState

@Singleton
class MainFlowCommandHolder @Inject constructor() {
    val openScreen = Command<NavCommand>()
}

class MainFlowReducer @Inject constructor() : Reducer<MainFlowEvent, MainFlowState> {

    override fun reduce(state: MainFlowState, event: MainFlowEvent): MainFlowState {
        return when (event) {
            else -> state
        }
    }
}