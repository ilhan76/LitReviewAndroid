package com.litreview.f_book_detail

import android.os.Bundle
import com.litreview.base.analytics.APP_METRICA_ADD_BOOKMARK
import com.litreview.base.analytics.APP_METRICA_DELETE_BOOKMARK
import com.litreview.base.storage.ReviewsBufferStorage
import com.litreview.base.util.Args
import com.litreview.base.util.DEFAULT_ERROR
import com.litreview.i_profile.ProfileInteractor
import com.litreview.f_book_detail.BookDetailEvent.*
import com.litreview.i_navigation.providers.BookDetailNavCommandProvider
import com.litreview.i_review.ReviewInteractor
import com.yandex.metrica.YandexMetrica
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
    private val navCommandProvider: BookDetailNavCommandProvider,
    private val reviewsBufferStorage: ReviewsBufferStorage
) : DslFlowMiddleware<BookDetailEvent> {

    private val state get() = flowState.currentState

    override fun transform(eventStream: Flow<BookDetailEvent>): Flow<BookDetailEvent> {
        return eventStream.transformations {
            addAll(
                OnCreateEvent::class eventToStream { checkAuthStatus() },
                UpdateBookEvent::class eventToEvent ::checkIsBookAdded,
                BookmarkClickEvent::class eventToStream ::handleBookmarkClick,
                OpenWriteReviewScreen::class eventToStream ::openWriteReviewScreen,
                OpenReviewsScreen::class eventToStream ::openReviewsScreen
            )
        }
    }

    private fun checkAuthStatus() = flow<BookDetailEvent> {
        emit(UpdateAuthStatus(profileInteractor.isAuthorized))
    }

    private fun checkIsBookAdded(event: UpdateBookEvent): BookDetailEvent {
        return UpdateIsAddedToBookmarksStatus(
            profileInteractor.isMyBook(event.book.id)
        )
    }

    private fun handleBookmarkClick(event: BookmarkClickEvent): Flow<BookDetailEvent> = flow {
        try {
            state.book?.let { book ->
                if (state.isAdded) {
                    profileInteractor.deleteBookToBookmarks(book)
                    YandexMetrica.reportEvent(APP_METRICA_ADD_BOOKMARK, book.id.toString())
                } else {
                    profileInteractor.addBookToBookmarks(book)
                    YandexMetrica.reportEvent(APP_METRICA_DELETE_BOOKMARK)
                }
                emit(UpdateIsAddedToBookmarksStatus(!state.isAdded))
            }
        } catch (e: Throwable) {
            ch.showFailAddToBookmarksMessage.accept(Unit)
        }
    }

    private fun openWriteReviewScreen(
        event: OpenWriteReviewScreen
    ): Flow<BookDetailEvent> = flow {
        state.book?.let { book ->
            ch.openScreen.accept(
                navCommandProvider.toWriteReview(
                    Bundle().apply {
                        putSerializable(Args.EXTRA_FIRST, book)
                    }
                )
            )
        }
    }

    private fun openReviewsScreen(
        event: OpenReviewsScreen
    ): Flow<BookDetailEvent> = flow {
        state.book?.let { book ->
            try {
                //todo - заменить на id
                reviewsBufferStorage.emit(reviewInteractor.getReviewsByBook(book.title))
                ch.openScreen.accept(navCommandProvider.toReviewsList)
            } catch (e: Throwable) {
                ch.showErrorMassage.accept(DEFAULT_ERROR)
            }
        }
    }
}