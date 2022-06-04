package com.litreview.f_feed

import android.os.Bundle
import com.litreview.base.util.Args
import com.litreview.base.util.DEFAULT_ERROR
import com.litreview.i_navigation.tabsNavigation.BottomTab
import com.litreview.i_navigation.tabsNavigation.TabsNavigationEventHub
import com.litreview.f_feed.FeedEvent.*
import com.litreview.i_books.BooksInteractor
import com.litreview.i_navigation.providers.TabsNavCommandProvider
import com.litreview.i_profile.ProfileInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class FeedMiddleware @Inject constructor(
    private val tabsNavigationEventHub: TabsNavigationEventHub,
    private val booksIteractor: BooksInteractor,
    private val profileInteractor: ProfileInteractor,
    private val ch: FeedCommandHolder,
    private val navCommandProvider: TabsNavCommandProvider
) : DslFlowMiddleware<FeedEvent> {

    override fun transform(eventStream: Flow<FeedEvent>): Flow<FeedEvent> {
        return eventStream.transformations {
            addAll(
                subscribeOnProfileInfo(),
                OnCreateEvent::class eventToStream { loadFeed() },
                LoadMyBooks::class eventToStream { loadMyBooks() },
                LoadNewBook::class eventToStream { loadNewBooks() },
                LoadBestBook::class eventToStream { loadBestBooks() },
                SearchViewClickEvent::class eventToStream { selectSearchTab() },
                OpenBookDetails::class eventToStream ::openBookDetails
            )
        }
    }

    private fun subscribeOnProfileInfo(): Flow<FeedEvent> = flow {
        profileInteractor.subscribeOnUserInfo().collect {
            emit(UpdateProfile(it))
        }
    }

    private fun loadMyBooks(): Flow<FeedEvent> = flow {
        try {
            emit(UpdateMyBooks(booksIteractor.getMyBooks()))
        } catch (e: Throwable) {
            ch.showErrorMessage.accept(e.message ?: DEFAULT_ERROR)
        }
    }

    private fun loadNewBooks(): Flow<FeedEvent> = flow {
        try {
            emit(UpdateNewBooks(booksIteractor.getNewBooks()))
        } catch (e: Throwable) {
            ch.showErrorMessage.accept(e.message ?: DEFAULT_ERROR)
        }
    }

    private fun loadBestBooks(): Flow<FeedEvent> = flow {
        try {
            emit(UpdateBestBooks(booksIteractor.getBestBooks()))
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

    private fun openBookDetails(event: OpenBookDetails): Flow<FeedEvent> = flow {
        ch.openTopScreen.accept(
            navCommandProvider.toBookDetail(
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