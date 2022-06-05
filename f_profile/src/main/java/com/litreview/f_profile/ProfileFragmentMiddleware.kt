package com.litreview.f_profile

import android.os.Bundle
import com.litreview.base.storage.BooksBufferStorage
import com.litreview.base.util.Args
import com.litreview.i_profile.ProfileInteractor
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import com.litreview.f_profile.ProfileFragmentEvent.*
import com.litreview.i_navigation.providers.TabsNavCommandProvider
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ProfileFragmentMiddleware @Inject constructor(
    private val flowState: FlowState<ProfileState>,
    private val ch: ProfileFragmentCommandHolder,
    private val profileInteractor: ProfileInteractor,
    private val navCommandProvider: TabsNavCommandProvider,
    private val booksBufferStorage: BooksBufferStorage
) : DslFlowMiddleware<ProfileFragmentEvent> {

    private val state get() = flowState.currentState

    override fun transform(eventStream: Flow<ProfileFragmentEvent>): Flow<ProfileFragmentEvent> {
        return eventStream.transformations {
            addAll(
                subscribeOnUserInfo(),
                OpenChangePersonalData::class eventToStream { openChangePersonalOffer() },
                OpenMyReviewEvent::class eventToStream { openMyReview() },
                OpenMyBooksEvent::class eventToStream { openMyBooks() },
                LogoutEvent::class eventToStream { logout() }
            )
        }
    }

    private fun subscribeOnUserInfo() : Flow<ProfileFragmentEvent> = flow {
        profileInteractor.subscribeOnUserInfo().collect {
            emit(UpdateProfileInfo(it))
        }
    }

    private fun openChangePersonalOffer(): Flow<ProfileFragmentEvent> = flow {
        ch.openTopScreen.accept(navCommandProvider.toChangePersonalData)
    }

    private fun openMyReview(): Flow<ProfileFragmentEvent> = flow {
        ch.openTopScreen.accept(navCommandProvider.toMyReview)
    }

    private fun openMyBooks(): Flow<ProfileFragmentEvent> = flow {
        state.userInfo?.let {
            ch.openTopScreen.accept(
                navCommandProvider.toBooksList(
                    Bundle().apply {
                        putString(Args.EXTRA_FIRST, "Мои книги")
                        booksBufferStorage.emitBooks(it.books)
                    }
                )
            )
        }
    }

    private fun logout(): Flow<ProfileFragmentEvent> = flow {
        profileInteractor.logout()
        ch.openTopScreen.accept(navCommandProvider.toStart)
    }
}
