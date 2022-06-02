package com.litreview.f_feed

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor() : MviStatefulViewModel<FeedState, FeedEvent>() {

    @Inject
    override lateinit var reducer: Reducer<FeedEvent, FeedState>

    @Inject
    override lateinit var state: FlowState<FeedState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<FeedEvent>
}