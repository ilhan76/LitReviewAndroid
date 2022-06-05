package com.litreview.f_profile

import com.litreview.base.data.domain.UserInfo
import com.litreview.base.util.Command
import com.litreview.i_navigation.NavCommand
import com.litreview.f_profile.ProfileFragmentEvent.*
import ru.surfstudio.mvi.core.reducer.Reducer
import javax.inject.Inject
import javax.inject.Singleton

data class ProfileState(
    val userInfo: UserInfo? = null
)

@Singleton
class ProfileFragmentCommandHolder @Inject constructor() {
    val openScreen = Command<NavCommand>()
    val openTopScreen = Command<NavCommand>()
    val showErrorMassage = Command<String>()
}

class ProfileFragmentReducer @Inject constructor() :
    Reducer<ProfileFragmentEvent, ProfileState> {

    override fun reduce(
        state: ProfileState,
        event: ProfileFragmentEvent
    ): ProfileState {
        return when (event) {
            is UpdateProfileInfo -> state.copy(userInfo = event.userInfo)
            else -> state
        }
    }
}