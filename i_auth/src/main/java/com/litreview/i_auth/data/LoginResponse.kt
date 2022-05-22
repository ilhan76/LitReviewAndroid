package com.litreview.i_auth.data

import com.google.gson.annotations.SerializedName
import com.litreview.base.data.Transformable
import com.litreview.base.data.domain.TokenInfo

data class LoginResponse(
    @SerializedName("access_token") val token: String
) : Transformable<TokenInfo> {

    override fun transform() = TokenInfo(token)

}