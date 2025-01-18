package com.example.gdg_c.data.repository

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
    ) {
        RetrofitInstance.taskService.postTask(
            TaskPostRequest(
                endDate,
                mustDoTasks,
                requirements,
                startDate,
                title,
                userIdentity
            )
        )
    }

    suspend fun getTasks() = RetrofitInstance.taskService.getTasks(1)
}