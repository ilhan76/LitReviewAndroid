package com.litreview.f_feed

import ru.surfstudio.mvi.core.event.Event

sealed class FeedEvent : Event {

    object SearchViewClickEvent : FeedEvent()
}