package com.litreview.base.data.dto

import com.google.gson.annotations.SerializedName
import com.litreview.base.data.Transformable
import com.litreview.base.data.domain.Author

data class AuthorDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("middleName")
    val middleName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("dateOfDeath")
    val dateOfDeath: String,
    @SerializedName("description")
    val description: String
) : Transformable<Author> {
    override fun transform(): Author {
        return Author(
            firstName = firstName,
            middleName = middleName,
            lastName = lastName,
            description = description
        )
    }
}