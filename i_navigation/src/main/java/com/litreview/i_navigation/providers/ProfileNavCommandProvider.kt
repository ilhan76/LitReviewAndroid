package com.litreview.i_navigation.providers

import com.litreview.i_navigation.NavCommand

interface ProfileNavCommandProvider {
    val toMyReview: NavCommand
    val toMyBooks: NavCommand
}