package com.litreview.f_book_detail

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(): MviStatefulViewModel<BookDetailState, BookDetailEvent>() {

    @Inject
    override lateinit var reducer: Reducer<BookDetailEvent, BookDetailState>

    @Inject
    override lateinit var state: FlowState<BookDetailState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<BookDetailEvent>
}