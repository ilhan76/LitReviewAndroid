package com.litreview.f_book_detail

import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

class BookDetailViewModel : MviStatefulViewModel<BookDetailState, BookDetailEvent>() {

    @Inject
    override lateinit var reducer: Reducer<BookDetailEvent, BookDetailState>

    @Inject
    override lateinit var state: FlowState<BookDetailState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<BookDetailEvent>
}