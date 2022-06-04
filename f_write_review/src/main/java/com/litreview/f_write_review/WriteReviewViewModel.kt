package com.litreview.f_write_review

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class WriteReviewViewModel @Inject constructor() :
    MviStatefulViewModel<WriteReviewState, WriteReviewEvent>() {

    @Inject
    override lateinit var reducer: Reducer<WriteReviewEvent, WriteReviewState>

    @Inject
    override lateinit var state: FlowState<WriteReviewState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<WriteReviewEvent>
}