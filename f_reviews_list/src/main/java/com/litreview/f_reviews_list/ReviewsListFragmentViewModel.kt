package com.litreview.f_reviews_list

import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

class ReviewsListFragmentViewModel : MviStatefulViewModel<ReviewsListFragmentState, ReviewsListFragmentEvent>() {

    @Inject
    override lateinit var reducer: Reducer<ReviewsListFragmentEvent, ReviewsListFragmentState>

    @Inject
    override lateinit var state: FlowState<ReviewsListFragmentState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<ReviewsListFragmentEvent>
}