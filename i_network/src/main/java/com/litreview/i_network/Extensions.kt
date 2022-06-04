package com.litreview.i_network

import com.litreview.base.network.NotAuthorizedException
import com.litreview.base.network.UnexpectedError
import retrofit2.Response

//todo - расширить обрабатываемые ошибки
fun <T> Response<T>.responseCheck(): Response<T> {
    val code = code()
    return when {
        code / 100 == 2 -> this
        code == 401 -> throw NotAuthorizedException()
        else -> throw UnexpectedError()
    }
}