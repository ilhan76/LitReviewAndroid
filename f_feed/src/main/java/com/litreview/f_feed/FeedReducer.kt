package com.litreview.f_feed

import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.UserInfo
import com.litreview.base.util.Command
import com.litreview.f_feed.FeedEvent.*
import com.litreview.i_navigation.NavCommand
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

data class FeedState(
    val userInfo: UserInfo? = null,
    val myBooks: List<Book> = emptyList(),
    val newBooks: List<Book> = emptyList(),
    val bestBooks: List<Book> = emptyList()
)

@Singleton
class FeedCommandHolder @Inject constructor() {
    val showErrorMessage = Command<String>()
    val openTopScreen = Command<NavCommand>()
}

class FeedReducer @Inject constructor() : Reducer<FeedEvent, FeedState> {

    override fun reduce(state: FeedState, event: FeedEvent): FeedState {
        return when (event) {
            is UpdateProfile -> state.copy(userInfo = event.userInfo)
            is UpdateBestBooks -> state.copy(bestBooks = event.books)
            is UpdateMyBooks -> state.copy(myBooks = event.books)
            is UpdateNewBooks -> state.copy(newBooks = event.books)
            else -> state
        }
    }
}