package com.litreview.base.storage

import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.Review
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksBufferStorage @Inject constructor() {

    private val sharedFlow = MutableSharedFlow<List<Book>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    suspend fun emit(list: List<Book>) {
        sharedFlow.emit(list)
    }

    fun get(): List<Book> {
        return sharedFlow.replayCache.first()
    }
}

@Singleton
class ReviewsBufferStorage @Inject constructor() {

    private val sharedFlow = MutableSharedFlow<List<Review>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    suspend fun emit(list: List<Review>) {
        sharedFlow.emit(list)
    }

    fun get(): List<Review> {
        return sharedFlow.replayCache.first()
    }
}