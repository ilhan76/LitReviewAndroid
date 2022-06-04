package com.litreview.i_review

import com.litreview.i_review.data.CreateReviewRequest
import com.litreview.i_network.Urls.Review.REVIEWS
import com.litreview.i_review.data.ReviewDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReviewApi {

    @GET(REVIEWS)
    suspend fun getAllReviews(): Response<List<ReviewDto>>

    @GET(REVIEWS)
    suspend fun getReviewsByBook(@Query("title") title: String): Response<List<ReviewDto>>

    @POST(REVIEWS)
    suspend fun sendReview(@Body request: CreateReviewRequest): Response<Unit>

}