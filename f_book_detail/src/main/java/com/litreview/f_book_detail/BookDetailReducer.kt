package com.litreview.f_book_detail

import com.litreview.base.util.Command
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

data class BookDetailState(
    val isAdded: Boolean = false
)

@Singleton
class BookDetailCommandHolder @Inject constructor() {
    val showFailAddToBookmarksMessage = Command<Unit>()
}

class BookDetailReducer @Inject constructor(): Reducer<BookDetailEvent, BookDetailState> {
    override fun reduce(state: BookDetailState, event: BookDetailEvent): BookDetailState {
        return when(event) {
            is BookDetailEvent.UpdateIsAddedToBookmarksStatus -> state.copy(isAdded = event.isAdded)
            else -> state
        }
    }
}