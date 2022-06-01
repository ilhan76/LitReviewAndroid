package com.litreview.f_profile

import com.litreview.base.data.domain.PublicUserInfo
import ru.surfstudio.mvi.core.event.Event

sealed class ProfileFragmentEvent : Event {

    object ViewCreatedEvent : ProfileFragmentEvent()

    object OpenMyReviewEvent : ProfileFragmentEvent()
    object OpenMyBooksEvent : ProfileFragmentEvent()
    object LogoutEvent : ProfileFragmentEvent()

    data class UpdateProfileInfo(
        val userInfo: PublicUserInfo
    ) : ProfileFragmentEvent()
}