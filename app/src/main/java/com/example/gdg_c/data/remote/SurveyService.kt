package com.example.gdg_c.data.remote

import com.example.gdg_c.data.model.request.UserPostRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface SurveyService {
    @POST("user")
    suspend fun postUser(
        @Body userPostRequest: UserPostRequest
    )
}