package com.example.gdg_c.data.remote

import com.example.gdg_c.data.model.repsonse.BaseResponse
import com.example.gdg_c.data.model.repsonse.schedule.TaskListResponse
import com.example.gdg_c.data.model.repsonse.schedule.TaskResponse
import com.example.gdg_c.data.model.request.TaskPostRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaskService {
    @POST("schedule/")
    suspend fun postTask(@Body taskPostRequest: TaskPostRequest) : BaseResponse<Any>

    @GET("schedule/")
    suspend fun getTasks(
        @Query("userIdentity") userIdentity: String,
        @Query("year") year: Int,
        @Query("month") month: Int
    ): BaseResponse<TaskListResponse>
}