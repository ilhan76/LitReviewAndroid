package com.litreview.f_feed

import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject

class FeedState

class FeedReducer @Inject constructor() : Reducer<FeedEvent, FeedState> {

    override fun reduce(state: FeedState, event: FeedEvent): FeedState {
        return state
    }
}