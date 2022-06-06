package com.litreview.i_navigation.providers

import com.litreview.i_navigation.NavCommand

interface MainNavCommandProvider {
    val toAuth: NavCommand
    val toRegister: NavCommand
    val toFeed: NavCommand
}