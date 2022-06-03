package com.litreview.i_navigation.tabsNavigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TabsNavigationEventHub @Inject constructor() {

    private val _flow =
        MutableSharedFlow<BottomTab>()
    val flow: SharedFlow<BottomTab> = _flow

    suspend fun emit(value: BottomTab) = _flow.emit(value)
}