package com.litreview.f_book_detail

import ru.surfstudio.mvi.core.reducer.Reducer

class BookDetailState

class BookDetailReducer: Reducer<BookDetailEvent, BookDetailState> {
    override fun reduce(state: BookDetailState, event: BookDetailEvent): BookDetailState {
        return state
    }
}