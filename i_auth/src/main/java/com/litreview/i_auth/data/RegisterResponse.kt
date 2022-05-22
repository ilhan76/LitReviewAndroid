package com.litreview.i_auth.data

import com.google.gson.annotations.SerializedName
import com.litreview.base.data.Transformable

data class RegisterResponse(
    @SerializedName("email")
    val email: String
) : Transformable<String> {

    override fun transform(): String = email

}