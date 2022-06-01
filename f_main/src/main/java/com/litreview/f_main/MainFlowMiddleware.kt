package com.litreview.f_main

import com.litreview.i_navigation.NavigationEventHub
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class MainFlowMiddleware @Inject constructor(
    private val navigationEventHub: NavigationEventHub,
    private val ch: MainFlowCommandHolder
) : DslFlowMiddleware<MainFlowEvent> {

    override fun transform(eventStream: Flow<MainFlowEvent>): Flow<MainFlowEvent> {
        return eventStream.transformations {
            addAll(
                subscribeNavigationHub()
            )
        }
    }

    private fun subscribeNavigationHub(): Flow<MainFlowEvent> = flow {
        navigationEventHub.flow.collect {
            ch.openScreen.accept(it)
        }
    }
}