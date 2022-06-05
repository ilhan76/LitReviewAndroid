package com.litreview.i_books

import com.litreview.base.data.domain.Book
import com.litreview.i_network.responseCheck
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val booksApi: BooksApi
) {

    suspend fun getBooksByTitle(title: String) : List<Book> {
        val response = booksApi.getBooksByTitle(title)
        return response.responseCheck().body()!!.map {
            it.transform()
        }
    }

    suspend fun getMyBooks(): List<Book> {
        val response = booksApi.getMyBooks()
        return response.responseCheck().body()!!.map {
            it.transform()
        }
    }

    suspend fun getNewBooks() : List<Book> {
        val response = booksApi.getNewBooks()
        return response.responseCheck().body()!!.map {
            it.transform()
        }
    }

    suspend fun getBestBooks() : List<Book> {
        val response = booksApi.getBestBooks()
        return response.responseCheck().body()!!.map {
            it.transform()
        }
    }
}