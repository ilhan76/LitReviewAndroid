package com.litreview.i_navigation.providers

import android.os.Bundle
import com.litreview.i_navigation.NavCommand

interface TabsNavCommandProvider {

    val toStart: NavCommand
    val toChangePersonalData: NavCommand

    val toMyReview: NavCommand

    fun toBooksList(args: Bundle): NavCommand
    fun toBookDetail(args: Bundle) : NavCommand
}