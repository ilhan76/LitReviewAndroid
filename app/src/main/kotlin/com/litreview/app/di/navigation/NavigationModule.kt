package com.litreview.app.di.navigation

import com.litreview.app.navigation.*
import com.litreview.i_navigation.providers.*
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

    @Provides
    fun provideRegisterCommandProvider() : RegisterNavCommandProvider {
        return RegisterNavCommandProviderImpl()
    }

    @Provides
    fun provideProfileCommandProvider() : ProfileNavCommandProvider {
        return ProfileNavCommandProviderImpl()
    }

    @Provides
    fun provideFeedCommandProvider() : FeedNavCommandProvider {
        return FeedNavCommandProviderImpl()
    }
}