package com.litreview.base.data.domain

import com.litreview.base.util.EMPTY_STRING
import java.io.Serializable

data class TokenInfo(
    val accessToken: String = EMPTY_STRING
) : Serializable
