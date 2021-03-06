package com.litreview.i_navigation

import android.os.Bundle
import androidx.navigation.NavOptions

data class NavCommand(
    val action: Int,
    val currentDestination: Int,
    var args: Bundle?= null,
    val navOptions: NavOptions? = null
)
