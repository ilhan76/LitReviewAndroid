package com.litreview.i_feed

import com.litreview.base.data.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedInteractor @Inject constructor(
    private val feedRepository: FeedRepository
) {

    suspend fun getMyBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            feedRepository.getMyBooks()
        }
    }

    suspend fun getNewBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            feedRepository.getNewBooks()
        }
    }

    suspend fun getBestBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            feedRepository.getBestBooks()
        }
    }
}