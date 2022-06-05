package com.litreview.f_book_detail

import android.os.Bundle
import com.litreview.base.storage.ReviewsBufferStorage
import com.litreview.base.util.Args
import com.litreview.base.util.DEFAULT_ERROR
import com.litreview.i_profile.ProfileInteractor
import com.litreview.f_book_detail.BookDetailEvent.*
import com.litreview.i_navigation.providers.TabsNavCommandProvider
import com.litreview.i_review.ReviewInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Inject

class BookDetailMiddleware @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val reviewInteractor: ReviewInteractor,
    private val flowState: FlowState<BookDetailState>,
    private val ch: BookDetailCommandHolder,
    private val navCommandProvider: TabsNavCommandProvider,
    private val reviewsBufferStorage: ReviewsBufferStorage
) : DslFlowMiddleware<BookDetailEvent> {

    private val state get() = flowState.currentState

    override fun transform(eventStream: Flow<BookDetailEvent>): Flow<BookDetailEvent> {
        return eventStream.transformations {
            addAll(
                CheckIsBookAdded::class eventToEvent ::checkIsBookAdded,
                BookmarkClickEvent::class eventToStream ::handleBookmarkClick,
                OpenWriteReviewScreen::class eventToStream ::openWriteReviewScreen,
                OpenReviewsScreen::class eventToStream ::openReviewsScreen
            )
        }
    }

    private fun checkIsBookAdded(event: CheckIsBookAdded): BookDetailEvent {
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

    private fun openWriteReviewScreen(
        event: OpenWriteReviewScreen
    ): Flow<BookDetailEvent> = flow {
        ch.openScreen.accept(
            navCommandProvider.toWriteReview(
                Bundle().apply {
                    putSerializable(Args.EXTRA_FIRST, event.book)
                }
            )
        )
    }

    private fun openReviewsScreen(
        event: OpenReviewsScreen
    ): Flow<BookDetailEvent> = flow {
        try {
            //todo - заменить на id
            reviewsBufferStorage.emit(reviewInteractor.getReviewsByBook(event.book.title))
            ch.openScreen.accept(navCommandProvider.toReviewsList)
        } catch (e: Throwable) {
            ch.showErrorMassage.accept(DEFAULT_ERROR)
        }
    }
}