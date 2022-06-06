package com.litreview.i_navigation.providers

import android.os.Bundle
import com.litreview.i_navigation.NavCommand

interface BookDetailNavCommandProvider {

    val toReviewsList: NavCommand

    fun toWriteReview(args: Bundle): NavCommand
}