package com.litreview.i_profile

import com.litreview.base.data.domain.UserInfo
import com.litreview.i_network.responseCheck
import retrofit2.Response
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: ProfileApi
) {

    suspend fun getUser() : UserInfo {
        val response = api.getProfile()
        return response.responseCheck().body()?.transform()!!
    }
}