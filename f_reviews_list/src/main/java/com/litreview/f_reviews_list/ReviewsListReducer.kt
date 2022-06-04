package com.litreview.f_reviews_list

import com.litreview.base.data.domain.Review
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject

data class ReviewsListState(
    val reviews: List<Review> = emptyList()
)

class ReviewsListReducer @Inject constructor() : Reducer<ReviewsListEvent, ReviewsListState> {

    override fun reduce(
        state: ReviewsListState,
        event: ReviewsListEvent
    ): ReviewsListState {
        return when (event) {
            else -> state
        }
    }
}