package com.litreview.app.navigation

import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.MainNavCommandProvider
import javax.inject.Inject

class MainNavCommandProviderImpl @Inject constructor() : MainNavCommandProvider {

    private val currentDestination = R.id.mainFragment

    override val toAuth: NavCommand
        get() = NavCommand(R.id.action_mainFlowFragment_to_authFragmentView, currentDestination)

    override val toRegister: NavCommand
        get() = NavCommand(R.id.action_mainFlowFragment_to_registerFragmentView, currentDestination)

    override val toFeed: NavCommand
        get() = NavCommand(R.id.action_mainFragment_to_mainFlowFragmentView, currentDestination)
}