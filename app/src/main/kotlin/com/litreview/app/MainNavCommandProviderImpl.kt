package com.litreview.app

import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.MainNavCommandProvider

class MainNavCommandProviderImpl: MainNavCommandProvider {
    override val toAuth: NavCommand
        get() = NavCommand(R.id.action_mainFlowFragment_to_authFragmentView)
    override val toRegister: NavCommand
        get() = NavCommand(R.id.action_mainFlowFragment_to_registerFragmentView)
}