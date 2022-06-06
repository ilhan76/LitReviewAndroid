package com.litreview.i_profile

import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.UserInfo
import com.litreview.base.data.domain.Review
import com.litreview.base.data.domain.toPublicUserInfo
import com.litreview.i_token.TokenStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileInteractor @Inject constructor(
    private val repository: ProfileRepository,
    private val tokenStorage: TokenStorage,
) {

    private val userSharedFlow = MutableStateFlow<UserInfo?>(null)
    private val reviewsStateFlow = MutableStateFlow<List<Review>>(emptyList())
    private val booksStateFlow = MutableStateFlow<List<Book>>(emptyList())

    val isAuthorized: Boolean
    get() = userSharedFlow.replayCache.isNotEmpty() &&
            userSharedFlow.replayCache.first() != null

    suspend fun getAndSaveUserInfo() {
        return withContext(Dispatchers.IO) {
            val user = repository.getUser()
            userSharedFlow.emit(user)
            reviewsStateFlow.emit(
                user.reviews.map {
                    it.copy(
                        userInfo = user.toPublicUserInfo()
                    )
                }
            )
            booksStateFlow.emit(user.books)
        }
    }

    fun subscribeOnUserInfo(): SharedFlow<UserInfo?> = userSharedFlow

    fun getMyReviews(): List<Review> {
        return reviewsStateFlow.replayCache.first()
    }

    fun getMyBooks(): List<Book> {
        return booksStateFlow.replayCache.first()
    }

    fun isMyBook(id: Int): Boolean {
        return booksStateFlow.replayCache.first().any {
            it.id == id
        }
    }

    suspend fun addBookToBookmarks(book: Book) {
        withContext(Dispatchers.IO) {
            repository.addBookToBookmarks(book.id.toString())
            booksStateFlow.emit(
                booksStateFlow.replayCache.first().plus(book)
            )
        }
    }

    suspend fun deleteBookToBookmarks(book: Book) {
        withContext(Dispatchers.IO) {
            repository.deleteBookToBookmarks(book.id.toString())
            booksStateFlow.emit(
                booksStateFlow.replayCache.first().minus(book)
            )
        }
    }

    @ExperimentalCoroutinesApi
    suspend fun logout() {
        tokenStorage.clearTokens()
        userSharedFlow.emit(null)
        reviewsStateFlow.emit(emptyList())
        booksStateFlow.emit(emptyList())
    }
}