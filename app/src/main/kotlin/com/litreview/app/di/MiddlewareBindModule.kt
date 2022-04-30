package com.litreview.app.di

import com.litreview.f_auth.AuthFragmentEvent
import com.litreview.f_auth.AuthFragmentMiddleware
import com.litreview.f_start.main_screen.MainFragmentEvent
import com.litreview.f_start.main_screen.MainFragmentMiddleware
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.surfstudio.mvi.flow.DslFlowMiddleware

@InstallIn(SingletonComponent::class)
@Module
interface MiddlewareBindModule {

    @Binds
    fun bindDslFlowMiddleware_to_AuthMeddleware(
        authFragmentMiddleware: AuthFragmentMiddleware
    ): DslFlowMiddleware<AuthFragmentEvent>

    @Binds
    fun bindDslFlowMiddleware_to_MainMeddleware(
        mainFragmentMiddleware: MainFragmentMiddleware
    ): DslFlowMiddleware<MainFragmentEvent>
}