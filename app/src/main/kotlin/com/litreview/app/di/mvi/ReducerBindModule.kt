package com.litreview.app.di.mvi

import com.litreview.f_book_detail.BookDetailEvent
import com.litreview.f_book_detail.BookDetailReducer
import com.litreview.f_book_detail.BookDetailState
import com.litreview.f_books_list.BooksListFragmentEvent
import com.litreview.f_books_list.BooksListFragmentReducer
import com.litreview.f_books_list.BooksListFragmentState
import com.litreview.f_feed.FeedEvent
import com.litreview.f_feed.FeedReducer
import com.litreview.f_feed.FeedState
import com.litreview.f_main.MainFlowEvent
import com.litreview.f_main.MainFlowReducer
import com.litreview.f_main.MainFlowState
import com.litreview.f_profile.ProfileFragmentEvent
import com.litreview.f_profile.ProfileFragmentReducer
import com.litreview.f_profile.ProfileFragmentState
import com.litreview.f_reviews_list.ReviewsListEvent
import com.litreview.f_reviews_list.ReviewsListReducer
import com.litreview.f_reviews_list.ReviewsListState
import com.litreview.f_start.splash.SplashEvent
import com.litreview.f_start.splash.SplashReducer
import com.litreview.f_start.splash.SplashState
import com.litreview.f_write_review.WriteReviewEvent
import com.litreview.f_write_review.WriteReviewReducer
import com.litreview.f_write_review.WriteReviewState
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

    @Binds
    fun bindReducerToReviewsListReducer(
        reviewsListReducer: ReviewsListReducer
    ) : Reducer<ReviewsListEvent, ReviewsListState>

    @Binds
    fun bindReducerToFeedReducer(
        feedReducer: FeedReducer
    ) : Reducer<FeedEvent, FeedState>

    @Binds
    fun bindReducerToSplashReducer(
        splashReducer: SplashReducer
    ) : Reducer<SplashEvent, SplashState>

    @Binds
    fun bindReducerToBookDetailReducer(
        bookDetailReducer: BookDetailReducer
    ) : Reducer<BookDetailEvent, BookDetailState>

    @Binds
    fun bindReducerToWriteReviewReducer(
        writeReviewReducer: WriteReviewReducer
    ) : Reducer<WriteReviewEvent, WriteReviewState>
}