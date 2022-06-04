package com.litreview.i_navigation.providers

import android.os.Bundle
import com.litreview.i_navigation.NavCommand

interface BookDetailNavCommandProvider {
    fun toReviews(args: Bundle): NavCommand
    fun toWriteReview(args: Bundle): NavCommand
}