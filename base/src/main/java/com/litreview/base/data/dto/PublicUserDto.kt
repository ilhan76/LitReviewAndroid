package com.litreview.base.data.dto

import com.google.gson.annotations.SerializedName
import com.litreview.base.data.Transformable
import com.litreview.base.data.domain.PublicUser

data class PublicUserDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("secondName")
    val secondName: String,
    @SerializedName("avatar")
    val avatarUrl: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("rate")
    val rate: Double?
) : Transformable<PublicUser> {
    override fun transform(): PublicUser {
        return PublicUser(id, firstName, secondName, avatarUrl, description, rate)
    }
}