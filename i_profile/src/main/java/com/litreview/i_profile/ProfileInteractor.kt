package com.litreview.i_profile

import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.UserInfo
import com.litreview.base.data.domain.Review
import com.litreview.i_token.TokenStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
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
        MutableSharedFlow<List<Review>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val booksSharedFlow =
        MutableSharedFlow<List<Book>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    suspend fun getAndSaveUserInfo() {
        return withContext(Dispatchers.IO) {
            val user = repository.getUser()
            userSharedFlow.emit(user)
            reviewsSharedFlow.emit(user.reviews)
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

    suspend fun addBookToBookmarks(id: String) {
        withContext(Dispatchers.IO) {
            repository.addBookToBookmarks(id)
        }
    }

    fun logout() {
        tokenStorage.clearTokens()
    }
}