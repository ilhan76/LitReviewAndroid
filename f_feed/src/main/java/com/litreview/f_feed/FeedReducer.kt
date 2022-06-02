package com.litreview.f_feed

import com.litreview.base.data.domain.Book
import com.litreview.base.util.Command
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

data class FeedState(
    val myBooks: List<Book> = emptyList(),
    val newBooks: List<Book> = emptyList(),
    val bestBooks: List<Book> = emptyList()
)

@Singleton
class FeedCommandHolder @Inject constructor() {
    val showErrorMessage = Command<String>()
}

class FeedReducer @Inject constructor() : Reducer<FeedEvent, FeedState> {

    override fun reduce(state: FeedState, event: FeedEvent): FeedState {
        return when (event) {
            is FeedEvent.UpdateBestBooks -> state.copy(bestBooks = event.books)
            is FeedEvent.UpdateMyBooks -> state.copy(myBooks = event.books)
            is FeedEvent.UpdateNewBooks -> state.copy(newBooks = event.books)
            else -> state
        }
    }
}