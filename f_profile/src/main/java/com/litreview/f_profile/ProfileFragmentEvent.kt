package com.litreview.f_profile

import com.litreview.base.data.domain.UserInfo
import ru.surfstudio.mvi.core.event.Event

sealed class ProfileFragmentEvent : Event {

    object OpenMyReviewEvent : ProfileFragmentEvent()
    object OpenMyBooksEvent : ProfileFragmentEvent()
    object OpenChangePersonalData : ProfileFragmentEvent()
    object LogoutEvent : ProfileFragmentEvent()

    data class UpdateProfileInfo(
        val userInfo: UserInfo?
    ) : ProfileFragmentEvent()
}