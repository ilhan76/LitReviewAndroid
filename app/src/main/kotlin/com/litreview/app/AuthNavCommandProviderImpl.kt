package com.litreview.app

import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.AuthNavCommandProvider

class AuthNavCommandProviderImpl: AuthNavCommandProvider {
    override val toMain: NavCommand
        get() = NavCommand(R.id.action_authFragmentView_to_mainFlowFragment)
    override val toFeed: NavCommand
        get() = NavCommand(R.id.action_authFragmentView_to_mainFlowFragmentView)
}