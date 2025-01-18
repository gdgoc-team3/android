package com.example.gdg_c.data.repository

import com.example.gdg_c.data.model.repsonse.BaseResponse
import com.example.gdg_c.data.model.repsonse.my.MyInfoResponse
import com.example.gdg_c.data.network.RetrofitInstance

class MyRepository {

    suspend fun getMyInfo(userIdentity: String): BaseResponse<MyInfoResponse> {
        val response = RetrofitInstance.myService.getMyInfo(userIdentity)

        return response
    }
}