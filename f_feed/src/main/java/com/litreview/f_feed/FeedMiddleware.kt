package com.litreview.f_feed

import com.litreview.base.util.DEFAULT_ERROR
import com.litreview.i_navigation.BottomTab
import com.litreview.i_navigation.TabsNavigationEventHub
import com.litreview.f_feed.FeedEvent.*
import com.litreview.i_feed.FeedInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class FeedMiddleware @Inject constructor(
    private val tabsNavigationEventHub: TabsNavigationEventHub,
    private val feedIteractor: FeedInteractor,
    private val ch: FeedCommandHolder
) : DslFlowMiddleware<FeedEvent> {

    override fun transform(eventStream: Flow<FeedEvent>): Flow<FeedEvent> {
        return eventStream.transformations {
            addAll(
                LoadFeed::class eventToStream { loadFeed() },
                LoadMyBooks::class eventToStream { loadMyBooks() },
                LoadNewBook::class eventToStream { loadNewBooks() },
                LoadBestBook::class eventToStream { loadBestBooks() },
                SearchViewClickEvent::class eventToStream { selectSearchTab() }
            )
        }
    }

    private fun loadMyBooks(): Flow<FeedEvent> = flow {
        try {
            emit(UpdateMyBooks(feedIteractor.getMyBooks()))
        } catch (e: Throwable) {
            ch.showErrorMessage.accept(e.message ?: DEFAULT_ERROR)
        }
    }

    private fun loadNewBooks(): Flow<FeedEvent> = flow {
        try {
            emit(UpdateNewBooks(feedIteractor.getNewBooks()))
        } catch (e: Throwable) {
            ch.showErrorMessage.accept(e.message ?: DEFAULT_ERROR)
        }
    }

    private fun loadBestBooks(): Flow<FeedEvent> = flow {
        try {
            emit(UpdateBestBooks(feedIteractor.getBestBooks()))
        } catch (e: Throwable) {
            ch.showErrorMessage.accept(e.message ?: DEFAULT_ERROR)
        }
    }

    private fun loadFeed(): Flow<FeedEvent> = flow {
        emit(LoadMyBooks)
        emit(LoadNewBook)
        emit(LoadBestBook)
    }

    private fun selectSearchTab(): Flow<FeedEvent> = flow {
        tabsNavigationEventHub.emit(BottomTab.SEARCH)
    }
}