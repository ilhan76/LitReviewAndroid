package com.litreview.i_auth

import com.litreview.i_token.TokenStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenStorage: TokenStorage
) {

    suspend fun login(
        email: String,
        password: String
    ) {
        val token = authRepository.login(email, password)
        tokenStorage.saveTokens(token)
    }

    suspend fun register(
        name: String,
        secondName: String,
        email: String,
        password: String,
        phone: String
    ): String {
        return authRepository.register(name, secondName, email, password, phone)
    }

}