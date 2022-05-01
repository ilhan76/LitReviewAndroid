package com.litreview.i_navigation.providers

import com.litreview.i_navigation.NavCommand

interface AuthNavCommandProvider {
    val toMain: NavCommand
    val toFeed: NavCommand
}