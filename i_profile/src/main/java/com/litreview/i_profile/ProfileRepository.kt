package com.litreview.i_profile

import com.litreview.base.data.domain.UserInfo
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: ProfileApi
) {

    suspend fun getUser() : UserInfo {
        val response = api.getProfile()
        return response.body()?.transform() ?: throw Exception(response.message())
    }
}