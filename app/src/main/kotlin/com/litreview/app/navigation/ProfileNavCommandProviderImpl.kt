package com.litreview.app.navigation

import android.os.Bundle
import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.ProfileNavCommandProvider
import javax.inject.Inject

class ProfileNavCommandProviderImpl @Inject constructor() : ProfileNavCommandProvider {

    private val currentDestination = R.id.mainFlowFragmentView

    override val toStart =
        NavCommand(
            R.id.action_mainFlowFragmentView_to_mainFragment,
            currentDestination
        )

    override val toChangePersonalData = NavCommand(
        R.id.action_mainFlowFragmentView_to_changePersonalDataView,
        currentDestination
    )

    override val toMyReview = NavCommand(
        R.id.action_mainFlowFragmentView_to_reviewsListFragmentView,
        currentDestination
    )

    override fun toMyBooks(args: Bundle) = NavCommand(
        action = R.id.action_mainFlowFragmentView_to_booksListFragmentView,
        currentDestination = currentDestination,
        args = args
    )
}