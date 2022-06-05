package com.litreview.f_search

import com.litreview.base.util.Command
import com.litreview.base.util.EMPTY_STRING
import com.litreview.f_search.SearchEvent.*
import com.litreview.i_navigation.NavCommand
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

data class SearchState(
    val searchText: String = EMPTY_STRING
)

@Singleton
class SearchCommandHolder @Inject constructor() {
    val openTopScreen = Command<NavCommand>()
    val showErrorMessage = Command<String>()
}

class SearchReducer @Inject constructor() : Reducer<SearchEvent, SearchState> {
    override fun reduce(state: SearchState, event: SearchEvent): SearchState {
        return when(event) {
            is UpdateSearchText -> state.copy(searchText = event.text)
            else -> state
        }
    }
}