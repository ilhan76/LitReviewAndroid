package com.litreview.f_write_review

import com.litreview.base.util.Command
import com.litreview.base.util.EMPTY_STRING
import com.litreview.f_write_review.WriteReviewEvent.*
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

data class WriteReviewState(
    val reviewText: String = EMPTY_STRING,
    val reviewRate: Int = 0
)

@Singleton
class WriteReviewCommandHolder @Inject constructor() {
    val showErrorMessage = Command<String>()
    val showMessage = Command<String>()
    val closeScreen = Command<Unit>()
}

class WriteReviewReducer @Inject constructor(
) : Reducer<WriteReviewEvent, WriteReviewState> {
    override fun reduce(state: WriteReviewState, event: WriteReviewEvent): WriteReviewState {
        return when (event) {
            is ReviewTextChanged -> state.copy(reviewText = event.text)
            is ReviewRateChanged -> state.copy(reviewRate = event.rate)
            else -> state
        }
    }
}