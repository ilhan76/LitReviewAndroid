package com.litreview.app.di.mvi

import com.litreview.f_books_list.BooksListFragmentEvent
import com.litreview.f_books_list.BooksListFragmentReducer
import com.litreview.f_books_list.BooksListFragmentState
import com.litreview.f_main.MainFlowEvent
import com.litreview.f_main.MainFlowReducer
import com.litreview.f_main.MainFlowState
import com.litreview.f_profile.ProfileFragmentEvent
import com.litreview.f_profile.ProfileFragmentReducer
import com.litreview.f_profile.ProfileFragmentState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.surfstudio.mvi.core.reducer.Reducer


@InstallIn(SingletonComponent::class)
@Module
interface ReducerBindModule {

    @Binds
    fun bindReducerToProfileReducer(
        profileFragmentReducer: ProfileFragmentReducer
    ) : Reducer<ProfileFragmentEvent, ProfileFragmentState>

    @Binds
    fun bindReducerToBooksListReducer(
        booksListFragmentReducer: BooksListFragmentReducer
    ) : Reducer<BooksListFragmentEvent, BooksListFragmentState>

    @Binds
    fun bindReducerToMainFlowReducer(
        mainFlowReducer: MainFlowReducer
    ) : Reducer<MainFlowEvent, MainFlowState>
}