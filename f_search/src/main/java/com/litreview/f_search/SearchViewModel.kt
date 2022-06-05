package com.litreview.f_search

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : MviStatefulViewModel<SearchState, SearchEvent>() {
    @Inject
    override lateinit var reducer: Reducer<SearchEvent, SearchState>

    @Inject
    override lateinit var state: FlowState<SearchState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<SearchEvent>
}