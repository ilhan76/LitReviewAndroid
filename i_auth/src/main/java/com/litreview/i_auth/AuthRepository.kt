package com.litreview.i_auth

import com.litreview.base.data.domain.TokenInfo
import com.litreview.i_auth.data.LoginRequest
import com.litreview.i_auth.data.RegisterRequest
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

    fun register(
        name: String,
        secondName: String,
        email: String,
        password: String,
        phone: String
    ): Flow<String> = flow {
        emit(
            authApi.register(
                RegisterRequest(
                    name = name,
                    secondName = secondName,
                    email = email,
                    password = password,
                    phone = phone
                )
            ).transform()
        )
    }

}