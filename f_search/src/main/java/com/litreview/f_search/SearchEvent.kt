package com.litreview.f_search

import ru.surfstudio.mvi.core.event.Event

sealed class SearchEvent : Event {

    object StartSearch : SearchEvent()

    data class UpdateSearchText(
        val text: String
    ): SearchEvent()
}