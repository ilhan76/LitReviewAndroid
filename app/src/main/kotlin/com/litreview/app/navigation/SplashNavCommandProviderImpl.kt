package com.litreview.app.navigation

import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.SplashNavProvider

class SplashNavCommandProviderImpl : SplashNavProvider {

    private val currentDestination = R.id.splashFragmentView

    override val toMain =
        NavCommand(R.id.action_splashFragmentView_to_mainFragment, currentDestination)
    override val toFeed =
        NavCommand(R.id.action_splashFragmentView_to_mainFlowFragmentView, currentDestination)
}