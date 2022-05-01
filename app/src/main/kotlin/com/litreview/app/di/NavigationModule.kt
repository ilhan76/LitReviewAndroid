package com.litreview.app.di

import com.litreview.app.navigation.AuthNavCommandProviderImpl
import com.litreview.app.navigation.MainNavCommandProviderImpl
import com.litreview.i_navigation.providers.AuthNavCommandProvider
import com.litreview.i_navigation.providers.MainNavCommandProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NavigationModule {

    @Provides
    fun provideAuthNavCommandProvider() : AuthNavCommandProvider {
        return AuthNavCommandProviderImpl()
    }

    @Provides
    fun provideMainNavCommandProvider() : MainNavCommandProvider {
        return MainNavCommandProviderImpl()
    }
}