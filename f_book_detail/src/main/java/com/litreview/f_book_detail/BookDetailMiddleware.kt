package com.litreview.f_book_detail

import com.litreview.i_profile.ProfileInteractor
import com.litreview.f_book_detail.BookDetailEvent.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Inject

class BookDetailMiddleware @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val flowState: FlowState<BookDetailState>,
    private val ch: BookDetailCommandHolder
) : DslFlowMiddleware<BookDetailEvent> {

    private val state get() = flowState.currentState

    override fun transform(eventStream: Flow<BookDetailEvent>): Flow<BookDetailEvent> {
        return eventStream.transformations {
            addAll(
                CheckIsBookAdded::class eventToEvent ::checkIsBookAdded,
                BookmarkClickEvent::class eventToStream ::handleBookmarkClick
            )
        }
    }

    private fun checkIsBookAdded(event: CheckIsBookAdded) : BookDetailEvent {
        return UpdateIsAddedToBookmarksStatus(
            profileInteractor.isMyBook(event.id)
        )
    }

    private fun handleBookmarkClick(event: BookmarkClickEvent): Flow<BookDetailEvent> = flow {
        if (state.isAdded) {
            // todo - когда будет запрос
        } else {
            try {
                profileInteractor.addBookToBookmarks(event.id)
                emit(UpdateIsAddedToBookmarksStatus(!state.isAdded))
            } catch (e: Throwable) {
                ch.showFailAddToBookmarksMessage.accept(Unit)
            }
        }
    }
}