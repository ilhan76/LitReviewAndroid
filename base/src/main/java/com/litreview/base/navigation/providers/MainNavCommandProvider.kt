package com.litreview.base.navigation.providers

import com.litreview.base.navigation.NavCommand

interface MainNavCommandProvider {
    val toAuth: NavCommand
    val toRegister: NavCommand
}