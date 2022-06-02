package com.litreview.f_feed

import com.litreview.i_navigation.BottomTab
import com.litreview.i_navigation.TabsNavigationEventHub
import com.litreview.f_feed.FeedEvent.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class FeedMiddleware @Inject constructor(
    private val tabsNavigationEventHub: TabsNavigationEventHub
) : DslFlowMiddleware<FeedEvent> {

    override fun transform(eventStream: Flow<FeedEvent>): Flow<FeedEvent> {
        return eventStream.transformations {
            addAll(
                SearchViewClickEvent::class eventToStream { selectSearchTab() }
            )
        }
    }

    private fun selectSearchTab(): Flow<FeedEvent> = flow {
        tabsNavigationEventHub.emit(BottomTab.SEARCH)
    }
}