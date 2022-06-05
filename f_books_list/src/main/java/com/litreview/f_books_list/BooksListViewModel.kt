package com.litreview.f_books_list

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class BooksListViewModel @Inject constructor() :
    MviStatefulViewModel<BooksListState, BooksListEvent>() {

    @Inject
    override lateinit var reducer: Reducer<BooksListEvent, BooksListState>

    @Inject
    override lateinit var state: FlowState<BooksListState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<BooksListEvent>
}