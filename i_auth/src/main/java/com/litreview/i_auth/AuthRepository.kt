package com.litreview.i_auth

import com.litreview.base.data.domain.TokenInfo
import com.litreview.i_auth.data.LoginRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {

    fun login(
        email: String,
        password: String
    ): Flow<TokenInfo> = flow {
        emit(
            authApi.login(LoginRequest(email, password)).transform()
        )
    }

}