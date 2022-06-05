package com.litreview.f_search

import android.content.Context
import androidx.core.os.bundleOf
import com.litreview.base.storage.BooksBufferStorage
import com.litreview.base.util.Args
import com.litreview.i_books.BooksInteractor
import com.litreview.f_search.SearchEvent.*
import com.litreview.i_navigation.providers.TabsNavCommandProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Inject

class SearchMiddleware @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val booksInteractor: BooksInteractor,
    private val flowState: FlowState<SearchState>,
    private val navCommandProvider: TabsNavCommandProvider,
    private val ch: SearchCommandHolder,
    private val booksBufferStorage: BooksBufferStorage
) : DslFlowMiddleware<SearchEvent> {

    private val state get() = flowState.currentState

    override fun transform(eventStream: Flow<SearchEvent>): Flow<SearchEvent> {
        return eventStream.transformations {
            addAll(
                StartSearch::class eventToStream { search() }
            )
        }
    }

    private fun search(): Flow<SearchEvent> = flow {
        try {
            val books = booksInteractor.getBooksByTitle(state.searchText)
            if (books.isNotEmpty()) {
                booksBufferStorage.emitBooks(books)
                ch.openTopScreen.accept(
                    navCommandProvider.toBooksList(
                        bundleOf(Args.EXTRA_FIRST to state.searchText)
                    )
                )
            } else {
                ch.showErrorMessage.accept(context.getString(R.string.not_found_search))
            }
        } catch (e: Throwable) {
            ch.showErrorMessage.accept(context.getString(R.string.error))
        }
    }
}