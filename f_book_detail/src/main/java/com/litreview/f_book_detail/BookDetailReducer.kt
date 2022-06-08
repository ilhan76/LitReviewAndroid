package com.litreview.f_book_detail

import com.litreview.base.data.domain.Book
import com.litreview.base.util.Command
import com.litreview.f_book_detail.BookDetailEvent.*
import com.litreview.i_navigation.NavCommand
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

data class BookDetailState(
    val isAuthorized: Boolean = false,
    val book: Book? = null,
    val isAdded: Boolean = false
)

@Singleton
class BookDetailCommandHolder @Inject constructor() {
    val showFailAddToBookmarksMessage = Command<Unit>()
    val showErrorMassage = Command<String>()
    val openScreen = Command<NavCommand>()
}

class BookDetailReducer @Inject constructor() : Reducer<BookDetailEvent, BookDetailState> {
    override fun reduce(state: BookDetailState, event: BookDetailEvent): BookDetailState {
        return when (event) {
            is UpdateAuthStatus -> state.copy(isAuthorized = event.isAuthorized)
            is UpdateBookEvent -> state.copy(book = event.book)
            is UpdateIsAddedToBookmarksStatus -> state.copy(isAdded = event.isAdded)
            else -> state
        }
    }
}