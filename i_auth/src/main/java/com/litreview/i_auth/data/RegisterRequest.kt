package com.litreview.i_auth.data

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("firstMame")
    val name: String,
    @SerializedName("secondName")
    val secondName: String,
    @SerializedName("phone")
    val phone: String
)