package com.litreview.i_auth

import com.litreview.i_auth.data.LoginRequest
import com.litreview.i_auth.data.LoginResponse
import com.litreview.i_network.Urls.Auth.LOGIN
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST(LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest) : LoginResponse
}