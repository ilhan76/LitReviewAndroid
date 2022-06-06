package com.litreview.app.navigation

import androidx.core.os.bundleOf
import com.litreview.R
import com.litreview.base.util.Args
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.SplashNavProvider

class SplashNavCommandProviderImpl : SplashNavProvider {

    private val currentDestination = R.id.splashFragmentView

    override val toMain =
        NavCommand(R.id.action_splashFragmentView_to_mainFragment, currentDestination)

    override val toFeed =
        NavCommand(
            action = R.id.action_splashFragmentView_to_mainFlowFragmentView,
            currentDestination = currentDestination,
            args = bundleOf(Args.EXTRA_FIRST to true)
        )
}