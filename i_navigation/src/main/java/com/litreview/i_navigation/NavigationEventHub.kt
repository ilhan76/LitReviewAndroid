package com.litreview.i_navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationEventHub @Inject constructor() {

    private val _flow =
        MutableSharedFlow<NavCommand>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val flow: SharedFlow<NavCommand> = _flow

    fun emit(value: NavCommand) = _flow.tryEmit(value)
}