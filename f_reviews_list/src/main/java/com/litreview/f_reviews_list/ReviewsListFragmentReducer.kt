package com.litreview.f_reviews_list

import ru.surfstudio.mvi.core.reducer.Reducer

class ReviewsListFragmentState

class ReviewsListFragmentReducer : Reducer<ReviewsListFragmentEvent, ReviewsListFragmentState> {
    override fun reduce(
        state: ReviewsListFragmentState,
        event: ReviewsListFragmentEvent
    ): ReviewsListFragmentState {
        return state
    }
}