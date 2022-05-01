package com.litreview.app.navigation

import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.AuthNavCommandProvider
import javax.inject.Inject

class AuthNavCommandProviderImpl @Inject constructor() : AuthNavCommandProvider {

    private val currentDestination = R.id.authFragmentView

    override val toMain: NavCommand
        get() = NavCommand(R.id.action_authFragmentView_to_mainFlowFragment, currentDestination)

    override val toFeed: NavCommand
        get() = NavCommand(R.id.action_authFragmentView_to_mainFlowFragmentView, currentDestination)
}