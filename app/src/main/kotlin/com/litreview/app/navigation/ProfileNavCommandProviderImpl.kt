package com.litreview.app.navigation

import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.ProfileNavCommandProvider
import javax.inject.Inject

class ProfileNavCommandProviderImpl @Inject constructor(): ProfileNavCommandProvider {

    private val currentDestination = R.id.mainFlowFragmentView

    override val toMyReview: NavCommand
        get() = NavCommand(R.id.action_mainFlowFragmentView_to_booksListFragmentView, currentDestination)

    override val toMyBooks: NavCommand
        get() = TODO("Not yet implemented")
}