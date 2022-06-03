package com.litreview.i_feed

import com.litreview.base.data.dto.BookDto
import retrofit2.Response
import retrofit2.http.GET
import com.litreview.i_network.Urls.Profile.MY_BOOKS
import com.litreview.i_network.Urls.Books.NEW_BOOKS
import com.litreview.i_network.Urls.Books.BEST_BOOKS

interface FeedApi {

    @GET(MY_BOOKS)
    suspend fun getMyBooks(): Response<List<BookDto>>

    @GET(NEW_BOOKS)
    suspend fun getNewBooks(): Response<List<BookDto>>

    @GET(BEST_BOOKS)
    suspend fun getBestBooks(): Response<List<BookDto>>
}