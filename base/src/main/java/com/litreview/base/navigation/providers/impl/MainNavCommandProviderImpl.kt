package com.litreview.base.navigation.providers.impl

import com.litreview.base.R
import com.litreview.base.navigation.NavCommand
import com.litreview.base.navigation.providers.MainNavCommandProvider

class MainNavCommandProviderImpl: MainNavCommandProvider {
    override val toAuth: NavCommand
        get() = NavCommand(R.id.action_mainFlowFragment_to_authFragmentView)
    override val toRegister: NavCommand
        get() = NavCommand(R.id.action_mainFlowFragment_to_registerFragmentView)
}