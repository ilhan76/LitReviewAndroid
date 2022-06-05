package com.litreview.i_navigation.providers

import android.os.Bundle
import com.litreview.i_navigation.NavCommand

interface TabsNavCommandProvider {

    val toStart: NavCommand
    val toChangePersonalData: NavCommand

    val toReviewsList: NavCommand

    fun toWriteReview(args: Bundle): NavCommand

    fun toBooksList(args: Bundle): NavCommand
    fun toBookDetail(args: Bundle) : NavCommand
}