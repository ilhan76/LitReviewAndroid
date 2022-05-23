package com.litreview.i_profile

import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.PublicUserInfo
import com.litreview.base.data.domain.Review
import com.litreview.base.mvi.Request
import com.litreview.base.mvi.Request.Loading
import com.litreview.base.mvi.Request.Success
import com.litreview.base.mvi.Request.Error
import com.litreview.base.mvi.io
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    val repository: ProfileRepository
) {

    fun getUserInfo(): Flow<Request<PublicUserInfo>> = flow {
        emit(Loading<PublicUserInfo>())
        repository.getUser()
            .catch {
                emit(Error<PublicUserInfo>(it))
            }.collect {
                emit(Success(it))
            }
    }.io()

    fun getMyReview(): Flow<Request<List<Review>>> = flow {
        emit(Loading<List<Review>>())
        repository.getMyReview()
            .catch {
                emit(Error<List<Review>>(it))
            }.collect {
                emit(Success(it))
            }
    }.io()

    fun getMyBooks(): Flow<Request<List<Book>>> = flow {
        emit(Loading<List<Book>>())
        repository.getMyBooks()
            .catch {
                emit(Error<List<Book>>(it))
            }.collect {
                emit(Success(it))
            }
    }.io()
}