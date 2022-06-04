package com.litreview.app.di.mvi

import com.litreview.f_auth.auth.AuthState
import com.litreview.f_auth.register.RegisterState
import com.litreview.f_book_detail.BookDetailState
import com.litreview.f_books_list.BooksListFragmentState
import com.litreview.f_feed.FeedState
import com.litreview.f_main.MainFlowState
import com.litreview.f_profile.ProfileFragmentState
import com.litreview.f_reviews_list.ReviewsListState
import com.litreview.f_start.splash.SplashState
import com.litreview.f_write_review.WriteReviewState
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

    @Provides
    @Singleton
    fun provideProfileFlowState(): FlowState<ProfileFragmentState> {
        return FlowState(ProfileFragmentState())
    }

    @Provides
    @Singleton
    fun provideBooksListFlowState() : FlowState<BooksListFragmentState> {
        return FlowState(BooksListFragmentState())
    }

    @Provides
    @Singleton
    fun provideMainFLowFlowState() : FlowState<MainFlowState> {
        return FlowState(MainFlowState())
    }

    @Provides
    @Singleton
    fun provideReviewsListFlowState() : FlowState<ReviewsListState> {
        return FlowState(ReviewsListState())
    }

    @Provides
    @Singleton
    fun provideFeedFlowState() : FlowState<FeedState> {
        return FlowState(FeedState())
    }

    @Provides
    @Singleton
    fun provideSplashFlowState() : FlowState<SplashState> {
        return FlowState(SplashState())
    }

    @Provides
    @Singleton
    fun provideBookDetailFlowState() : FlowState<BookDetailState> {
        return FlowState(BookDetailState())
    }

    @Provides
    @Singleton
    fun provideWriteReviewFlowState() : FlowState<WriteReviewState> {
        return FlowState(WriteReviewState())
    }
}