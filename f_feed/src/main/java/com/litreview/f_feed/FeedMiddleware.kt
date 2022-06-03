package com.litreview.f_feed

import android.os.Bundle
import com.litreview.base.util.Args
import com.litreview.base.util.DEFAULT_ERROR
import com.litreview.i_navigation.tabsNavigation.BottomTab
import com.litreview.i_navigation.tabsNavigation.TabsNavigationEventHub
import com.litreview.f_feed.FeedEvent.*
import com.litreview.i_feed.FeedInteractor
import com.litreview.i_navigation.providers.FeedNavCommandProvider
import com.litreview.i_profile.ProfileInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class FeedMiddleware @Inject constructor(
    private val tabsNavigationEventHub: TabsNavigationEventHub,
    private val feedIteractor: FeedInteractor,
    private val profileInteractor: ProfileInteractor,
    private val ch: FeedCommandHolder,
    private val navCommandProvider: FeedNavCommandProvider
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