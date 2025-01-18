package com.example.gdg_c.data.remote

import com.example.gdg_c.data.model.request.TaskPostRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskService {
    @POST("schedule")
    suspend fun postTask(
        @Body taskPostRequest: TaskPostRequest
    )
}