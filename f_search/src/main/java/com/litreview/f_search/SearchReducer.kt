package com.litreview.f_search

import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject

class SearchState

class SearchReducer @Inject constructor() : Reducer<SearchEvent, SearchState> {
    override fun reduce(state: SearchState, event: SearchEvent): SearchState {
        return when(event) {
            else -> state
        }
    }
}