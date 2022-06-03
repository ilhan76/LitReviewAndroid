package com.litreview.f_start.splash

import com.litreview.base.util.Command
import com.litreview.i_navigation.NavCommand
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

class SplashState

@Singleton
class SplashCommandHolder @Inject constructor() {
    val openScreen = Command<NavCommand>()
}

class SplashReducer @Inject constructor() : Reducer<SplashEvent, SplashState> {
    override fun reduce(state: SplashState, event: SplashEvent): SplashState {
        return state
    }
}