package com.litreview.app.di

import com.litreview.f_auth.auth.AuthState
import com.litreview.f_auth.register.RegisterState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.surfstudio.mvi.flow.FlowState
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class StateModule {

    @Provides
    @Singleton
    fun provideAuthFlowState(): FlowState<AuthState> {
        return FlowState(AuthState())
    }

    @Provides
    @Singleton
    fun provideRegisterFlowState(): FlowState<RegisterState> {
        return FlowState(RegisterState())
    }
}