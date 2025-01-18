package com.example.gdg_c.data.remote

import com.example.gdg_c.data.model.repsonse.BaseResponse
import com.example.gdg_c.data.model.repsonse.my.MyInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MyService {
    @GET("user/my/{userIdentity}")
    suspend fun getMyInfo(
        @Path("userIdentity") userIdentity: String
    ): BaseResponse<MyInfoResponse>
}