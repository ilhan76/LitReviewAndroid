package com.litreview.f_books_list

import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject

class BooksListState

class BooksListReducer @Inject constructor() :
    Reducer<BooksListEvent, BooksListState> {

    override fun reduce(
        state: BooksListState,
        event: BooksListEvent
    ): BooksListState {
        return when (event) {
            else -> state
        }
    }
}