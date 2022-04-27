package com.litreview.base.navigation.providers.impl

import com.litreview.base.R
import com.litreview.base.navigation.NavCommand
import com.litreview.base.navigation.providers.AuthNavCommandProvider

class AuthNavCommandProviderImpl: AuthNavCommandProvider {
    override val toMain: NavCommand
        get() = NavCommand(R.id.action_authFragmentView_to_mainFlowFragment)
    override val toFeed: NavCommand
        get() = TODO("Not yet implemented")
}