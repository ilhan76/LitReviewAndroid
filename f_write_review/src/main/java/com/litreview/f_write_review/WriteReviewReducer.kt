package com.litreview.f_write_review

import com.litreview.base.util.Command
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

class WriteReviewState

@Singleton
class WriteReviewCommandHolder @Inject constructor() {
    val showErrorMessage = Command<String>()
}

class WriteReviewReducer @Inject constructor() : Reducer<WriteReviewEvent, WriteReviewState> {
    override fun reduce(state: WriteReviewState, event: WriteReviewEvent): WriteReviewState {
        return state
    }
}