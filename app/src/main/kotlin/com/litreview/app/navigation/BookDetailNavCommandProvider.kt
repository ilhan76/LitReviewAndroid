package com.litreview.app.navigation

import android.os.Bundle
import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.BookDetailNavCommandProvider

class BookDetailNavCommandProviderImpl : BookDetailNavCommandProvider {

    private val currentDestination = R.id.bookDetailFragmentView

    override val toReviewsList: NavCommand
        get() = NavCommand(
            R.id.action_bookDetailFragmentView_to_reviewsListFragmentView,
            currentDestination
        )

    override fun toWriteReview(args: Bundle): NavCommand {
        return NavCommand(
            action = R.id.action_bookDetailFragmentView_to_writeReviewFragmentView,
            currentDestination = currentDestination,
            args = args
        )
    }
}