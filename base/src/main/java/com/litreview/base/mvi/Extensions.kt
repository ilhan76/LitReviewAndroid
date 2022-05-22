package com.litreview.base.mvi

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

fun <T> Flow<T>.io() = this.flowOn(Dispatchers.IO)
