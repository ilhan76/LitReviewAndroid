package com.litreview.f_profile

import com.litreview.base.util.DEFAULT_ERROR
import com.litreview.i_profile.ProfileInteractor
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import com.litreview.f_profile.ProfileFragmentEvent.*
import com.litreview.i_navigation.providers.ProfileNavCommandProvider
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ProfileFragmentMiddleware @Inject constructor(
    private val state: FlowState<ProfileFragmentState>,
    private val ch: ProfileFragmentCommandHolder,
    private val profileInteractor: ProfileInteractor,
    private val navCommandProvider: ProfileNavCommandProvider
) : DslFlowMiddleware<ProfileFragmentEvent> {

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
        ch.openTopScreen.accept(navCommandProvider.toMyBooks)
    }

    private fun logout(): Flow<ProfileFragmentEvent> = flow {
        profileInteractor.logout()
        ch.openTopScreen.accept(navCommandProvider.toStart)
    }
}
