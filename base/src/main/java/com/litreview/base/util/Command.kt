package com.litreview.base.util

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class Command<T> {

    private val _flow =
        MutableSharedFlow<T>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val flow: SharedFlow<T> = _flow

    fun accept(value: T) = _flow.tryEmit(value)
}