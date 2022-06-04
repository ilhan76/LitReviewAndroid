package com.litreview.app.di.mvi

import com.litreview.f_auth.auth.AuthFragmentEvent
import com.litreview.f_auth.auth.AuthFragmentMiddleware
import com.litreview.f_auth.register.RegisterFragmentEvent
import com.litreview.f_auth.register.RegisterFragmentMiddleware
import com.litreview.f_book_detail.BookDetailEvent
import com.litreview.f_book_detail.BookDetailMiddleware
import com.litreview.f_books_list.BooksListFragmentEvent
import com.litreview.f_books_list.BooksListFragmentMiddleware
import com.litreview.f_feed.FeedEvent
import com.litreview.f_feed.FeedMiddleware
import com.litreview.f_main.MainFlowEvent
import com.litreview.f_main.MainFlowMiddleware
import com.litreview.f_profile.ProfileFragmentEvent
import com.litreview.f_profile.ProfileFragmentMiddleware
import com.litreview.f_reviews_list.ReviewsListEvent
import com.litreview.f_reviews_list.ReviewsListMiddleware
import com.litreview.f_start.main_screen.MainFragmentEvent
import com.litreview.f_start.main_screen.MainFragmentMiddleware
import com.litreview.f_start.splash.SplashEvent
import com.litreview.f_start.splash.SplashMiddleware
import com.litreview.f_write_review.WriteReviewEvent
import com.litreview.f_write_review.WriteReviewMiddleware
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.surfstudio.mvi.flow.DslFlowMiddleware

@InstallIn(SingletonComponent::class)
@Module
interface MiddlewareBindModule {

    @Binds
    fun bindDslFlowMiddlewareToAuthMiddleware(
        authFragmentMiddleware: AuthFragmentMiddleware
    ): DslFlowMiddleware<AuthFragmentEvent>

    @Binds
    fun bindDslFlowMiddlewareToMainMiddleware(
        mainFragmentMiddleware: MainFragmentMiddleware
    ): DslFlowMiddleware<MainFragmentEvent>

    @Binds
    fun bindDslFlowMiddlewareToRegisterMiddleware(
        registerFragmentMiddleware: RegisterFragmentMiddleware
    ): DslFlowMiddleware<RegisterFragmentEvent>

    @Binds
    fun bindDslFlowMiddlewareToProfileMiddleware(
        profileFragmentMiddleware: ProfileFragmentMiddleware
    ): DslFlowMiddleware<ProfileFragmentEvent>

    @Binds
    fun bindDslFlowMiddlewareToBooksListMiddleware(
        booksListFragmentMiddleware: BooksListFragmentMiddleware
    ): DslFlowMiddleware<BooksListFragmentEvent>

    @Binds
    fun bindDslFlowMiddlewareToMainFlowMiddleware(
        mainFlowFragmentView: MainFlowMiddleware
    ): DslFlowMiddleware<MainFlowEvent>

    @Binds
    fun bindDslFlowMiddlewareToReviewsListMiddleware(
        reviewsListMiddleware: ReviewsListMiddleware
    ): DslFlowMiddleware<ReviewsListEvent>

    @Binds
    fun bindDslFlowMiddlewareToFeedMiddleware(
        feedMiddleware: FeedMiddleware
    ): DslFlowMiddleware<FeedEvent>

    @Binds
    fun bindDslFlowMiddlewareToSplashMiddleware(
        splashMiddleware: SplashMiddleware
    ): DslFlowMiddleware<SplashEvent>

    @Binds
    fun bindDslFlowMiddlewareToBookDetailMiddleware(
        bookDetailMiddleware: BookDetailMiddleware
    ): DslFlowMiddleware<BookDetailEvent>

    @Binds
    fun bindDslFlowMiddlewareToWriteReviewMiddleware(
        writeReviewMiddleware: WriteReviewMiddleware
    ): DslFlowMiddleware<WriteReviewEvent>
}
