package com.litreview.f_start.splash

import ru.surfstudio.mvi.core.event.Event

sealed class SplashEvent : Event {
    object TryToLoadProfile : SplashEvent()
    object OpenFeedScreenEvent : SplashEvent()
    object OpenMainScreenEvent : SplashEvent()
}