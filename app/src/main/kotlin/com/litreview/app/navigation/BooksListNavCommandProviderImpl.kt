package com.litreview.app.navigation

import android.os.Bundle
import com.litreview.R
import com.litreview.i_navigation.NavCommand
import com.litreview.i_navigation.providers.BooksListNavCommandProvider

class BooksListNavCommandProviderImpl: BooksListNavCommandProvider {

    private val currentDestination = R.id.booksListFragmentView

    override fun toBookDetail(args: Bundle) = NavCommand(
        R.id.action_booksListFragmentView_to_bookDetailFragmentView,
        currentDestination,
        args
    )
}