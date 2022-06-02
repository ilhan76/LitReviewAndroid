package com.litreview.i_profile

import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.UserInfo
import com.litreview.base.data.domain.Review
import com.litreview.i_token.TokenStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val repository: ProfileRepository,
    private val tokenStorage: TokenStorage
) {

    private val reviewsSharedFlow = MutableSharedFlow<List<Review>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val booksSharedFlow = MutableSharedFlow<List<Book>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    suspend fun getUserInfo(): UserInfo {
        return withContext(Dispatchers.IO){
            val user = repository.getUser()
            reviewsSharedFlow.emit(user.reviews)
            booksSharedFlow.emit(user.books)
            user
        }
    }

    suspend fun getMyReviews(): List<Review> {
        return withContext(Dispatchers.IO) {
            reviewsSharedFlow.replayCache.first()
        }
    }

    suspend fun getMyBooks(): List<Book>{
        return withContext(Dispatchers.IO) {
            booksSharedFlow.replayCache.first()
        }
    }

    fun logout() {
        tokenStorage.clearTokens()
    }
}