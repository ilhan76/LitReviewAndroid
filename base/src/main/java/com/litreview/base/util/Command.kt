package com.litreview.base.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class Command<T> {

    private val _flow =
        MutableSharedFlow<T>()
    val flow: SharedFlow<T> = _flow

    suspend fun accept(value: T) = _flow.emit(value)
}