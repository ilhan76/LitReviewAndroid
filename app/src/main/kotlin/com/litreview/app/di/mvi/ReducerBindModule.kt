package com.litreview.app.di.mvi

import com.litreview.f_profile.ProfileFragmentEvent
import com.litreview.f_profile.ProfileFragmentReducer
import com.litreview.f_profile.ProfileFragmentState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.surfstudio.mvi.core.reducer.Reducer


@InstallIn(SingletonComponent::class)
@Module
interface ReducerBindModule {

    @Binds
    fun bindReducerToProfileReducer(
        profileFragmentReducer: ProfileFragmentReducer
    ) : Reducer<ProfileFragmentEvent, ProfileFragmentState>
}