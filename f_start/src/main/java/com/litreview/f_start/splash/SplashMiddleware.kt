package com.litreview.f_start.splash

import com.litreview.i_profile.ProfileInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import com.litreview.f_start.splash.SplashEvent.*
import com.litreview.i_navigation.providers.SplashNavProvider
import javax.inject.Inject

class SplashMiddleware @Inject constructor(
    private val profileInteractor: ProfileInteractor,
    private val navProvider: SplashNavProvider,
    private val ch: SplashCommandHolder
) : DslFlowMiddleware<SplashEvent> {

    override fun transform(eventStream: Flow<SplashEvent>): Flow<SplashEvent> {
        return eventStream.transformations {
            addAll(
                TryToLoadProfile::class eventToStream { tryLoadProfile() },
                OpenFeedScreenEvent::class eventToStream { openFeed() },
                OpenMainScreenEvent::class eventToStream { openMain() }
            )
        }
    }

    private fun tryLoadProfile(): Flow<SplashEvent> = flow {
        try {
            profileInteractor.getAndSaveUserInfo()
            emit(OpenFeedScreenEvent)
        } catch (e: Throwable) {
            emit(OpenMainScreenEvent)
        }
    }

    private fun openFeed(): Flow<SplashEvent> = flow {
        ch.openScreen.accept(navProvider.toFeed)
    }

    private fun openMain(): Flow<SplashEvent> = flow {
        ch.openScreen.accept(navProvider.toMain)
    }
}