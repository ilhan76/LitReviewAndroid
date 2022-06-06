package com.litreview.i_profile

import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.UserInfo
import com.litreview.base.data.domain.Review
import com.litreview.base.data.domain.toPublicUserInfo
import com.litreview.i_token.TokenStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import java.util.Collections.addAll
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileInteractor @Inject constructor(
    private val repository: ProfileRepository,
    private val tokenStorage: TokenStorage,
) {

    private val userSharedFlow =
        MutableSharedFlow<UserInfo>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val reviewsSharedFlow =
        MutableStateFlow<List<Review>>(emptyList())
    private val booksSharedFlow =
        MutableStateFlow<List<Book>>(emptyList())

    val isAuthorized: Boolean get() = userSharedFlow.replayCache.isNotEmpty()

    suspend fun getAndSaveUserInfo() {
        return withContext(Dispatchers.IO) {
            val user = repository.getUser()
            userSharedFlow.emit(user)
            reviewsSharedFlow.emit(
                user.reviews.map {
                    it.copy(
                        userInfo = user.toPublicUserInfo()
                    )
                }
            )
            booksSharedFlow.emit(user.books)
        }
    }

    fun subscribeOnUserInfo(): SharedFlow<UserInfo> = userSharedFlow

    fun getMyReviews(): List<Review> {
        return reviewsSharedFlow.replayCache.first()
    }

    fun getMyBooks(): List<Book> {
        return booksSharedFlow.replayCache.first()
    }

    fun isMyBook(id: Int): Boolean {
        return booksSharedFlow.replayCache.first().any {
            it.id == id
        }
    }

    suspend fun addBookToBookmarks(book: Book) {
        withContext(Dispatchers.IO) {
            repository.addBookToBookmarks(book.id.toString())
            booksSharedFlow.emit(
                booksSharedFlow.replayCache.first().plus(book)
            )
        }
    }

    suspend fun deleteBookToBookmarks(book: Book) {
        withContext(Dispatchers.IO) {
            repository.deleteBookToBookmarks(book.id.toString())
            booksSharedFlow.emit(
                booksSharedFlow.replayCache.first().minus(book)
            )
        }
    }

    fun logout() {
        tokenStorage.clearTokens()
    }
}