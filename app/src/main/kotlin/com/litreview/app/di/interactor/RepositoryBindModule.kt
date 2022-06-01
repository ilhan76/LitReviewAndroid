package com.litreview.app.di.interactor

import com.litreview.i_profile.ProfileRepository
import com.litreview.i_profile.ProfileRepositoryStub
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryBindModule {

    @Binds
    fun bindProfileRepoToStub(
        profileRepositoryStub: ProfileRepositoryStub
    ): ProfileRepository
}