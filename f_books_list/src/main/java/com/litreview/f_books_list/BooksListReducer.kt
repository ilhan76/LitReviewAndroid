package com.litreview.f_books_list

import com.litreview.base.util.Command
import com.litreview.i_navigation.NavCommand
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

class BooksListState

@Singleton
class BooksListCommandHolder @Inject constructor() {
    val openTopScreen = Command<NavCommand>()
}

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