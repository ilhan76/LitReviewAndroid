package com.litreview.app.navigation

import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.MainNavCommandProvider
import javax.inject.Inject

class MainNavCommandProviderImpl @Inject constructor() : MainNavCommandProvider {
    override val toAuth: NavCommand
        get() = NavCommand(R.id.action_mainFlowFragment_to_authFragmentView)
    override val toRegister: NavCommand
        get() = NavCommand(R.id.action_mainFlowFragment_to_registerFragmentView)
}