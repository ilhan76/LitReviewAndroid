package com.litreview.i_auth

import com.litreview.base.data.domain.TokenInfo
import com.litreview.i_auth.data.LoginRequest
import com.litreview.i_auth.data.RegisterRequest
import com.litreview.i_network.responseCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {

    suspend fun login(
        email: String,
        password: String
    ): TokenInfo {
        return authApi.login(LoginRequest(email, password))
            .responseCheck()
            .body()!!.transform()
    }

    suspend fun register(
        name: String,
        secondName: String,
        email: String,
        password: String,
        phone: String
    ): String {
        return authApi.register(
            RegisterRequest(
                name = name,
                secondName = secondName,
                email = email,
                password = password,
                phone = phone
            )
        ).responseCheck()
            .body()!!.transform()
    }
}