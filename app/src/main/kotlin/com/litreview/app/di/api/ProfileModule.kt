package com.litreview.app.di.api

import com.litreview.i_profile.ProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProfileModule {

    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit) : ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }
}