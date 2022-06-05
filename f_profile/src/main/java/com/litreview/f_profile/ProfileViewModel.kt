package com.litreview.f_profile

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.surfstudio.mvi.core.reducer.Reducer
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import ru.surfstudio.mvi.flow.FlowState
import ru.surfstudio.mvi.vm.MviStatefulViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() :
    MviStatefulViewModel<ProfileState, ProfileFragmentEvent>() {

    @Inject
    override lateinit var reducer: Reducer<ProfileFragmentEvent, ProfileState>

    @Inject
    override lateinit var state: FlowState<ProfileState>

    @Inject
    override lateinit var middleware: DslFlowMiddleware<ProfileFragmentEvent>
}