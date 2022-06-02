package com.litreview.f_books_list

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class BooksListFragmentViewModel @Inject constructor() :
    MviStatefulViewModel<BooksListFragmentState, BooksListFragmentEvent>() {

    @Inject
    override lateinit var reducer: Reducer<BooksListFragmentEvent, BooksListFragmentState>

    @Inject
    override lateinit var state: FlowState<BooksListFragmentState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<BooksListFragmentEvent>
}