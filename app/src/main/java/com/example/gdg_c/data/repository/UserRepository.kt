package com.example.gdg_c.data.repository

import com.example.gdg_c.data.model.request.UserPostRequest
import com.example.gdg_c.data.network.RetrofitInstance

class UserRepository {
    suspend fun postUser(
        birthDate: String,
        nickname: String,
        userIdentity: String,
        major: String,
        desiredJob: String,
        targetEmploymentPeriod: Int

    ) {
        RetrofitInstance.surveyService.postUser(
            UserPostRequest(
                birthDate,
                nickname,
                userIdentity,
                major,
                desiredJob,
                targetEmploymentPeriod,
            )
        )
    }
}