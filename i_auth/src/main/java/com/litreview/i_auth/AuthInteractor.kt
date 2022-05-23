package com.litreview.i_auth

import com.litreview.base.mvi.Request
import com.litreview.base.mvi.Request.Error
import com.litreview.base.mvi.Request.Success
import com.litreview.base.mvi.Request.Loading
import com.litreview.base.mvi.io
import com.litreview.i_token.TokenStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenStorage: TokenStorage
) {

    fun login(
        email: String,
        password: String
    ): Flow<Request<Unit>> = flow {
        emit(Loading())
        authRepository.login(email, password)
            .catch { e ->
                emit(Error<Unit>(e))
            }.collect {
                tokenStorage.saveTokens(it)
                emit(Success(Unit))
            }
    }.io()

    fun register(
        name: String,
        secondName: String,
        email: String,
        password: String,
        phone: String
    ): Flow<Request<String>> = flow {
        emit(Loading())
        authRepository.register(name, secondName, email, password, phone)
            .catch { e -> emit(Error<String>(e)) }
            .collect {
                emit(Success(it))
            }
    }

}