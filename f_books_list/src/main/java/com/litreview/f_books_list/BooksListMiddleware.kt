package com.litreview.f_books_list

import android.os.Bundle
import com.litreview.base.storage.BooksBufferStorage
import com.litreview.base.util.Args
import com.litreview.i_navigation.providers.TabsNavCommandProvider
import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import com.litreview.f_books_list.BooksListEvent.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BooksListMiddleware @Inject constructor(
    private val tabsNavCommandProvider: TabsNavCommandProvider,
    private val ch: BooksListCommandHolder,
    private val booksBufferStorage: BooksBufferStorage
) : DslFlowMiddleware<BooksListEvent> {

    override fun transform(eventStream: Flow<BooksListEvent>): Flow<BooksListEvent> {
        return eventStream.transformations {
            addAll(
                GetBooksFromBuffer::class eventToEvent { getBooksFromBuffer() },
                OpenBookDetailsScreen::class eventToStream ::openBookDetails
            )
        }
    }

    private fun getBooksFromBuffer(): BooksListEvent {
        val books = booksBufferStorage.getBooks()
        return UpdateBooksList(books)
    }

    private fun openBookDetails(
        event: OpenBookDetailsScreen
    ): Flow<BooksListEvent> = flow {
        ch.openTopScreen.accept(
            tabsNavCommandProvider.toBookDetail(
                Bundle().apply {
                    putSerializable(
                        Args.EXTRA_FIRST,
                        event.book
                    )
                }
            )
        )
    }
}