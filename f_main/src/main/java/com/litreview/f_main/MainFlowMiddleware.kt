package com.litreview.f_main

import com.litreview.i_profile.ProfileInteractor
import com.litreview.f_main.MainFlowEvent.*
import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class MainFlowMiddleware @Inject constructor(
    private val profileInteractor: ProfileInteractor
) : DslFlowMiddleware<MainFlowEvent> {

    override fun transform(eventStream: Flow<MainFlowEvent>): Flow<MainFlowEvent> {
        return eventStream.transformations {
            addAll(
                CheckAuthStatus::class eventToEvent { checkAuthStatus() }
            )
        }
    }

    private fun checkAuthStatus(): MainFlowEvent {
        return UpdateAuthState(profileInteractor.isAuthorized)
    }
}