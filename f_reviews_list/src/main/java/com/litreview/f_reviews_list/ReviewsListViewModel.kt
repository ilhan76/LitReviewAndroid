package com.litreview.f_reviews_list

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewsListViewModel @Inject constructor() :
    MviStatefulViewModel<ReviewsListState, ReviewsListEvent>() {

    @Inject
    override lateinit var reducer: Reducer<ReviewsListEvent, ReviewsListState>

    @Inject
    override lateinit var state: FlowState<ReviewsListState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<ReviewsListEvent>
}