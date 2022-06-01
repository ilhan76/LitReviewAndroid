package com.litreview.f_profile

import com.litreview.base.data.domain.PublicUserInfo
import com.litreview.base.util.Command
import com.litreview.i_navigation.NavCommand
import com.litreview.f_profile.ProfileFragmentEvent.*
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

data class ProfileFragmentState(
    val userInfo: PublicUserInfo? = null
)

@Singleton
class ProfileFragmentCommandHolder @Inject constructor() {
    val openScreen = Command<NavCommand>()
    val openTopScreen = Command<NavCommand>()
    val showErrorMessage = Command<String>()
}

class ProfileFragmentReducer @Inject constructor() :
    Reducer<ProfileFragmentEvent, ProfileFragmentState> {

    override fun reduce(
        state: ProfileFragmentState,
        event: ProfileFragmentEvent
    ): ProfileFragmentState {
        return when (event) {
            is UpdateProfileInfo -> state.copy(userInfo = event.userInfo)
            else -> state
        }
    }
}