package com.litreview.i_auth.data

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    val login: String,
    @SerializedName("password")
    val password: String
)