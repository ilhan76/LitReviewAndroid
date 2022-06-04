package com.litreview.base.storage

import com.litreview.base.data.domain.Book
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksBufferStorage @Inject constructor() {

    private val sharedFlow = MutableSharedFlow<List<Book>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    suspend fun emitBooks(list: List<Book>) {
        sharedFlow.emit(list)
    }

    fun getBooks(): List<Book> {
        return sharedFlow.replayCache.first()
    }
}