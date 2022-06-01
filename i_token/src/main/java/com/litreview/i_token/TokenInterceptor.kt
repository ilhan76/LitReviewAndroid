package com.litreview.i_token

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val tokenStorage: TokenStorage
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${tokenStorage.accessToken}")
            .build()
        return chain.proceed(request)
    }
}