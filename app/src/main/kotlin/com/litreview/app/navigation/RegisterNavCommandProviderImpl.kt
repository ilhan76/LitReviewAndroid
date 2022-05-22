package com.litreview.app.navigation

import android.os.Bundle
import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.RegisterNavCommandProvider

class RegisterNavCommandProviderImpl: RegisterNavCommandProvider {

    private val currentDestination = R.id.registerFragmentView

    override fun toAuth(args: Bundle): NavCommand {
        return NavCommand(
            action = R.id.action_registerFragmentView_to_authFragmentView,
            currentDestination = currentDestination,
            args = args
        )
    }
}