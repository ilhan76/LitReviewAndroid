package com.litreview.i_navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

fun NavController.open(navCommand: NavCommand) {
    navigate(
        navCommand.action,
        navCommand.args,
        navCommand.navOptions
    )
}

fun Fragment.findNavControllerSafely(): NavController? {
    return if (isAdded) {
        findNavController()
    } else {
        null
    }
}