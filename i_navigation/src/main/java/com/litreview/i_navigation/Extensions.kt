package com.litreview.i_navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

fun NavController.open(navCommand: NavCommand) {
    if (currentDestination?.id == navCommand.currentDestination) {
        navigate(
            navCommand.action,
            navCommand.args,
            navCommand.navOptions
        )
    }
}

fun Fragment.findNavControllerSafely(): NavController? {
    return if (isAdded) {
        findNavController()
    } else {
        null
    }
}

fun Fragment.findTopNavControllerSafely(): NavController? {
    return if (isAdded) {
        val topLevelHost = requireActivity()
            .supportFragmentManager
            .findFragmentById(com.litreview.base.R.id.container_view) as NavHostFragment
        topLevelHost.navController
    } else {
        null
    }
}