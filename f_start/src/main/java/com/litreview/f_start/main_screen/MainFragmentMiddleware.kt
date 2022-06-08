package com.litreview.f_start.main_screen

import com.litreview.i_navigation.providers.MainNavCommandProvider
import com.litreview.f_start.main_screen.MainFragmentEvent.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware
import javax.inject.Inject

class MainFragmentMiddleware @Inject constructor(
    private val navCommandProvider: MainNavCommandProvider,
    private val ch: MainFragmentCommandHolder
) : DslFlowMiddleware<MainFragmentEvent> {

    override fun transform(eventStream: Flow<MainFragmentEvent>): Flow<MainFragmentEvent> {
        return eventStream.transformations {
            addAll(
                SignInClicked::class eventToStream { openSignInScreen() },
                SignUpClicked::class eventToStream { openSignUpScreen() },
                ContinueAsGuest::class eventToStream { openFeed() }
            )
        }
    }

    private fun openSignInScreen(): Flow<MainFragmentEvent> = flow {
        ch.openScreen.accept(navCommandProvider.toAuth)
    }

    private fun openSignUpScreen(): Flow<MainFragmentEvent> = flow {
        ch.openScreen.accept(navCommandProvider.toRegister)
    }

    private fun openFeed(): Flow<MainFragmentEvent> = flow {
        ch.openScreen.accept(navCommandProvider.toFeed)
    }
}