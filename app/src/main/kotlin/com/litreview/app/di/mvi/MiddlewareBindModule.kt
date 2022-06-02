package com.litreview.app.di.mvi

import com.litreview.f_auth.auth.AuthFragmentEvent
import com.litreview.f_auth.auth.AuthFragmentMiddleware
import com.litreview.f_auth.register.RegisterFragmentEvent
import com.litreview.f_auth.register.RegisterFragmentMiddleware
import com.litreview.f_books_list.BooksListFragmentEvent
import com.litreview.f_books_list.BooksListFragmentMiddleware
import com.litreview.f_feed.FeedEvent
import com.litreview.f_feed.FeedMiddleware
import com.litreview.f_main.MainFlowEvent
import com.litreview.f_main.MainFlowMiddleware
import com.litreview.f_profile.ProfileFragmentEvent
import com.litreview.f_profile.ProfileFragmentMiddleware
import com.litreview.f_reviews_list.ReviewsListFragmentEvent
import com.litreview.f_reviews_list.ReviewsListFragmentMiddleware
import com.litreview.f_start.main_screen.MainFragmentEvent
import com.litreview.f_start.main_screen.MainFragmentMiddleware
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
        reviewsListFragmentMiddleware: ReviewsListFragmentMiddleware
    ): DslFlowMiddleware<ReviewsListFragmentEvent>

    @Binds
    fun bindDslFlowMiddlewareToFeedMiddleware(
        feedMiddleware: FeedMiddleware
    ): DslFlowMiddleware<FeedEvent>
}
