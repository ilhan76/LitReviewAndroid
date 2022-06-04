package com.litreview.i_navigation.providers

import android.os.Bundle
import com.litreview.i_navigation.NavCommand

interface ProfileNavCommandProvider {
    val toStart: NavCommand
    val toChangePersonalData: NavCommand

    val toMyReview: NavCommand

    fun toMyBooks(args: Bundle): NavCommand
}