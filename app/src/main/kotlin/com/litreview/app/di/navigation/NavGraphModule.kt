package com.litreview.app.di.navigation

import com.litreview.R
import com.litreview.base.di.AuthNavGraph
import com.litreview.base.di.NavGraph
import com.litreview.base.di.NotAuthNavGraph
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NavGraphModule {

    @Provides
    @AuthNavGraph
    fun provideAuthNavGraph() : NavGraph {
        return NavGraph(R.navigation.auth_tabs_nav_graph)
    }

    @Provides
    @NotAuthNavGraph
    fun provideNotAuthNavGraph() : NavGraph {
        return NavGraph(R.navigation.not_auth_nav_graph)
    }
}