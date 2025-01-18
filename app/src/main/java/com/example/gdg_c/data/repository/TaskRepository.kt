package com.example.gdg_c.data.repository

import android.util.Log
import com.example.gdg_c.data.model.repsonse.BaseResponse
import com.example.gdg_c.data.model.request.TaskPostRequest
import com.example.gdg_c.data.network.RetrofitInstance

class TaskRepository {
    suspend fun postTask(
        endDate: String,
        mustDoTasks: String,
        requirements: String,
        startDate: String,
        title: String,
        userIdentity: String
    ): BaseResponse<Any> {
        // 파라미터 다 로그 찍어봐
        Log.d(
            "parameter",
            "endDate: $endDate, mustDoTasks: $mustDoTasks, requirements: $requirements, startDate: $startDate, title: $title, userIdentity: $userIdentity"
        )

        val response = RetrofitInstance.taskService.postTask(
            TaskPostRequest(
                endDate,
                mustDoTasks,
                requirements,
                startDate,
                title,
                userIdentity
            )
        )
        return response
    }

    suspend fun getTasks() = RetrofitInstance.taskService.getTasks(1)
}