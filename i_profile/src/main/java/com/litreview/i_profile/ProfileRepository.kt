package com.litreview.i_profile

import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.PublicUserInfo
import com.litreview.base.data.domain.Review
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getUser() : PublicUserInfo

    fun getMyReview() : Flow<List<Review>>

    fun getMyBooks() : Flow<List<Book>>
}