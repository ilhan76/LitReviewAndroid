package com.litreview.app.di.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.litreview.base.storage.SHARED_PREF_FILE_NAME
import com.litreview.base.storage.SharedPrefStorage
import com.litreview.base.storage.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class StorageModule {

    @Provides
    fun provideStorage(@ApplicationContext context: Context) : Storage {
        return SharedPrefStorage(context.getSharedPreferences(SHARED_PREF_FILE_NAME, MODE_PRIVATE))
    }
}