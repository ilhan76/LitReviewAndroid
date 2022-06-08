package com.litreview.f_write_review

import android.content.Context
import com.litreview.base.analytics.APP_MATRICA_WRITE_REVIEW
import kotlinx.coroutines.flow.Flow
import com.litreview.f_write_review.WriteReviewEvent.*
import com.litreview.i_review.ReviewInteractor
import com.yandex.metrica.YandexMetrica
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Inject

class WriteReviewMiddleware @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val reviewInteractor: ReviewInteractor,
    private val ch: WriteReviewCommandHolder,
    private val flowState: FlowState<WriteReviewState>
) : DslFlowMiddleware<WriteReviewEvent> {

    private val state get() = flowState.currentState

    override fun transform(eventStream: Flow<WriteReviewEvent>): Flow<WriteReviewEvent> {
        return eventStream.transformations {
            addAll(
                SendReview::class eventToStream ::handleSendBtnClick
            )
        }
    }

    private fun handleSendBtnClick(event: SendReview): Flow<WriteReviewEvent> = flow {
        when {
            state.reviewText.length < REVIEW_AVAILABLE_LENGTH -> ch.showErrorMessage.accept(
                context.getString(
                    R.string.snack_error_not_valid_text,
                    REVIEW_AVAILABLE_LENGTH.toString()
                )
            )
            state.reviewRate == 0 -> ch.showErrorMessage.accept(
                context.getString(
                    R.string.snack_error_not_valid_rating
                )
            )
            else -> {
                try {
                    reviewInteractor.sendReview(
                        bookId = event.book.id.toString(),
                        text = state.reviewText,
                        rate = state.reviewRate
                    )
                    ch.showMessage.accept(context.getString(R.string.snack_thanks_for_review))
                    ch.closeScreen.accept(Unit)
                    YandexMetrica.reportEvent(APP_MATRICA_WRITE_REVIEW)
                } catch (e: Throwable) {
                    acceptShowSnackCommand(R.string.snack_error_something_bad)
                }
            }
        }
    }

    private suspend fun acceptShowSnackCommand(stringRes: Int) {
        ch.showErrorMessage.accept(
            context.getString(stringRes)
        )
    }
}