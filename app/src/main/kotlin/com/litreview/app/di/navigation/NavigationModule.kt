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
    fun provideRegisterNavCommandProvider() : RegisterNavCommandProvider {
        return RegisterNavCommandProviderImpl()
    }

    @Provides
    fun provideFeedNavCommandProvider() : TabsNavCommandProvider {
        return TabsNavCommandProviderImpl()
    }

    @Provides
    fun provideSplashNavCommandProvider() : SplashNavProvider {
        return SplashNavCommandProviderImpl()
    }
}