package com.litreview.i_books

import com.litreview.base.data.domain.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksInteractor @Inject constructor(
    private val booksRepository: BooksRepository
) {

    suspend fun getBooksByTitle(title: String) : List<Book> {
        return withContext(Dispatchers.IO) {
            booksRepository.getBooksByTitle(title)
        }
    }

    suspend fun getMyBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            booksRepository.getMyBooks().take(10)
        }
    }

    suspend fun getNewBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            booksRepository.getNewBooks().take(10)
        }
    }

    suspend fun getBestBooks(): List<Book> {
        return withContext(Dispatchers.IO) {
            booksRepository.getBestBooks().take(10)
        }
    }
}