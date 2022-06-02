package com.litreview.f_books_list

import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject

class BooksListFragmentState

class BooksListFragmentReducer @Inject constructor() :
    Reducer<BooksListFragmentEvent, BooksListFragmentState> {

    override fun reduce(
        state: BooksListFragmentState,
        event: BooksListFragmentEvent
    ): BooksListFragmentState {
        return when (event) {
            else -> state
        }
    }
}