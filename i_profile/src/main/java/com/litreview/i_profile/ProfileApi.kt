package com.litreview.i_profile

import com.litreview.base.data.domain.Book
import com.litreview.i_network.Urls.Profile.PROFILE
import com.litreview.i_network.Urls.Profile.GET_BOOKS
import com.litreview.i_network.Urls.Profile.ADD_BOOK
import com.litreview.i_profile.data.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface ProfileApi {

    @GET(PROFILE)
    suspend fun getProfile() : Response<ProfileResponse>

    @GET(GET_BOOKS)
    suspend fun getBooks() : Response<List<Book>>

    @PUT(ADD_BOOK)
    suspend fun addBook(@Query("") id: String)
}