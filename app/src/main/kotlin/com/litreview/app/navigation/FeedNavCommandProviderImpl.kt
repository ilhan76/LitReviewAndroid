package com.litreview.app.navigation

import android.os.Bundle
import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.FeedNavCommandProvider

class FeedNavCommandProviderImpl : FeedNavCommandProvider {

    private val currentDestination = R.id.mainFlowFragmentView

    override fun toBookDetail(args: Bundle) = NavCommand(
        R.id.action_mainFlowFragmentView_to_bookDetailFragmentView,
        currentDestination,
        args
    )
}