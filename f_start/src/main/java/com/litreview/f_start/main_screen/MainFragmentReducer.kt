package com.litreview.f_start.main_screen

import ru.surfstudio.mvi.core.reducer.Reducer

data class MainFragmentState(
    val loadState: Int = 0 // todo - заглушка, чтобы не забыть про лоадстейт
)

class MainFragmentReducer: Reducer<MainFragmentEvent, MainFragmentState> {

    override fun reduce(state: MainFragmentState, event: MainFragmentEvent): MainFragmentState {
        return when (event) {
            else -> state
        }
    }
}