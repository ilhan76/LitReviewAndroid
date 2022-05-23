package com.litreview.f_profile

import com.litreview.i_profile.ProfileInteractor
import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Inject

class ProfileFragmentMiddleware @Inject constructor(
    private val state: FlowState<ProfileFragmentState>,
    private val ch: ProfileFragmentCommandHolder,
    private val profileInteractor: ProfileInteractor
) : DslFlowMiddleware<ProfileFragmentEvent> {

    override fun transform(eventStream: Flow<ProfileFragmentEvent>): Flow<ProfileFragmentEvent> {
        return eventStream.transformations {
            addAll()
        }
    }
}