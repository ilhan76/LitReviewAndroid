package com.litreview.i_review

import com.litreview.i_review.data.CreateReviewRequest
import com.litreview.i_network.Urls.Profile.PROFILE
import com.litreview.i_review.data.ReviewDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReviewApi {

    @GET(PROFILE)
    suspend fun getAllReviews(): Response<List<ReviewDto>>

    @GET(PROFILE)
    suspend fun getReviewsByBook(@Query("title") title: String): Response<List<ReviewDto>>

    @POST(PROFILE)
    suspend fun sendReview(@Body request: CreateReviewRequest): Response<Unit>

}