package com.litreview.i_profile

import com.litreview.i_network.Urls.Profile.PROFILE
import com.litreview.i_network.Urls.Profile.ADD_BOOK
import com.litreview.i_profile.data.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileApi {

    @GET(PROFILE)
    suspend fun getProfile() : Response<ProfileResponse>

    @PUT("$ADD_BOOK/{id}")
    suspend fun addBook(@Path("id") id: String)
}