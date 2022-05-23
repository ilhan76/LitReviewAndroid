package com.litreview.i_profile

import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.PublicUserInfo
import com.litreview.base.data.domain.Review
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryStub @Inject constructor(): ProfileRepository {
    override fun getUser(): Flow<PublicUserInfo> = flow {
        emit(
            PublicUserInfo(
                name = "Лол",
                secondName = "Кеков"
            )
        )
    }

    override fun getMyReview(): Flow<List<Review>> = flow {
        emit(
            listOf(
                Review(
                    text = "Какой-то текст",
                    rate = 4.0,
                    book = Book(
                        authorId = 1,
                        title = "Book",
                        image = "",
                        rate = 5.0
                    )
                )
            )
        )
    }

    override fun getMyBooks(): Flow<List<Book>> = flow {
        emit(
            listOf(
                Book(
                    authorId = 1,
                    title = "Book",
                    image = "",
                    rate = 5.0
                )
            )
        )
    }
}