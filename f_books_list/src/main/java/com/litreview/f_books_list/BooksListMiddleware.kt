package com.litreview.f_books_list

import android.os.Bundle
import com.litreview.base.util.Args
import com.litreview.i_navigation.providers.TabsNavCommandProvider
import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import com.litreview.f_books_list.BooksListEvent.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BooksListMiddleware @Inject constructor(
    private val tabsNavCommandProvider: TabsNavCommandProvider,
    private val ch: BooksListCommandHolder
) : DslFlowMiddleware<BooksListEvent> {

    override fun transform(eventStream: Flow<BooksListEvent>): Flow<BooksListEvent> {
        return eventStream.transformations {
            addAll(
                OpenBookDetailsScreen::class eventToStream ::openBookDetails
            )
        }
    }

    private fun openBookDetails(
        event: OpenBookDetailsScreen
    ): Flow<BooksListEvent> = flow {
        ch.openTopScreen.accept(
            tabsNavCommandProvider.toBookDetail(
                Bundle().apply {
                    putParcelable(
                        Args.EXTRA_FIRST,
                        event.book
                    )
                }
            )
        )
    }
}