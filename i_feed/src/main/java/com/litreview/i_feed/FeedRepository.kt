package com.litreview.i_feed

import com.litreview.base.data.domain.Book
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val feedApi: FeedApi
) {

    suspend fun getMyBooks(): List<Book> {
        val response = feedApi.getMyBooks()
        return response.body()?.map {
            it.transform()
        } ?: throw Exception(response.message())
    }

    suspend fun getNewBooks() : List<Book> {
        val response = feedApi.getNewBooks()
        return response.body()?.map {
            it.transform()
        } ?: throw Exception(response.message())
    }

    suspend fun getBestBooks() : List<Book> {
        val response = feedApi.getBestBooks()
        return response.body()?.map {
            it.transform()
        } ?: throw Exception(response.message())
    }
}