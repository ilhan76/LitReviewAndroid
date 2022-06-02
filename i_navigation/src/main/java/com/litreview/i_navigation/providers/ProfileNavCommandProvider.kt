package com.litreview.i_navigation.providers

import com.litreview.i_navigation.NavCommand

interface ProfileNavCommandProvider {
    val toChangePersonalData: NavCommand
    val toMyReview: NavCommand
    val toMyBooks: NavCommand
    val toStart: NavCommand
}