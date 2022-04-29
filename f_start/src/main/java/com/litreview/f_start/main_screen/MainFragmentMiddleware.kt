package com.litreview.f_start.main_screen

import com.litreview.i_navigation.open
import com.litreview.i_navigation.providers.MainNavCommandProvider
import com.litreview.f_start.main_screen.MainFragmentEvent.*
import kotlinx.coroutines.flow.Flow
import ru.surfstudio.mvi.flow.DslFlowMiddleware

class MainFragmentMiddleware(
    private val navCommandProvider: MainNavCommandProvider
) : DslFlowMiddleware<MainFragmentEvent> {

    override fun transform(eventStream: Flow<MainFragmentEvent>): Flow<MainFragmentEvent> {
        return eventStream.transformations {
            addAll(
                SignInClicked::class react ::openSignInScreen,
                SignUpClicked::class react ::openSignUpScreen
            )
        }
    }

    private fun openSignInScreen(event: SignInClicked) {
        with(event) {
            navController?.open(navCommandProvider.toAuth)
        }
    }

    private fun openSignUpScreen(event: SignUpClicked) {
        with(event) {
            navController?.open(navCommandProvider.toRegister)
        }
    }
}