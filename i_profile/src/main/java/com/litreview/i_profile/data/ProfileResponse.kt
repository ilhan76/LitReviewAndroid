package com.litreview.i_profile.data

import com.google.gson.annotations.SerializedName
import com.litreview.base.data.Transformable
import com.litreview.base.data.domain.Book
import com.litreview.base.data.domain.Review
import com.litreview.base.data.domain.UserInfo

data class ProfileResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("secondName")
    val secondName: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("rate")
    val rate: Double?,
    @SerializedName("reviews")
    val reviews: List<Review>,
    @SerializedName("books")
    val books: List<Book>
) : Transformable<UserInfo> {

    override fun transform(): UserInfo {
        return UserInfo(
            email = email,
            firstName = firstName,
            secondName = secondName,
            phone = phone,
            description = description,
            avatarUrl = avatar,
            rate = rate,
            reviews = reviews,
            books = books
        )
    }

}