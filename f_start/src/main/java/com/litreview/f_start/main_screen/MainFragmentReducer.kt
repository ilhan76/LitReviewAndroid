package com.litreview.f_start.main_screen

import com.litreview.base.util.Command
import com.litreview.i_navigation.NavCommand
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

data class MainFragmentState(
    val loadState: Int = 0 // todo - заглушка, чтобы не забыть про лоадстейт
)

@Singleton
class MainFragmentCommandHolder @Inject constructor() {
    val openScreen = Command<NavCommand>()
}

class MainFragmentReducer @Inject constructor() : Reducer<MainFragmentEvent, MainFragmentState> {

    override fun reduce(state: MainFragmentState, event: MainFragmentEvent): MainFragmentState {
        return when (event) {
            else -> state
        }
    }
}