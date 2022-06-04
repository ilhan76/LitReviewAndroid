package com.litreview.app.navigation

import android.os.Bundle
import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.BookDetailNavCommandProvider

class BookDetailNavCommandProviderImpl : BookDetailNavCommandProvider {
    private val currentDestination = R.id.bookDetailFragmentView

    override fun toReviews(args: Bundle) = NavCommand(
        action = R.id.action_bookDetailFragmentView_to_reviewsListFragmentView,
        currentDestination = currentDestination,
        args = args
    )

    override fun toWriteReview(args: Bundle) = NavCommand(
        action = R.id.action_bookDetailFragmentView_to_writeReviewFragmentView,
        currentDestination = currentDestination,
        args = args
    )
}