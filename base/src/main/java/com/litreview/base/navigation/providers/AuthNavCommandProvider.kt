package com.litreview.base.navigation.providers

import com.litreview.base.navigation.NavCommand

interface AuthNavCommandProvider {
    val toMain: NavCommand
    val toFeed: NavCommand
}