package com.example.gdg_c.data.model.repsonse.schedule


import com.google.gson.annotations.SerializedName

class TaskListResponse : ArrayList<TaskListResponse.TaskListResponseItem>(){
    data class TaskListResponseItem(
        val endDate: String,
        val mustDoTasks: String,
        val requirements: String,
        val scheduleId: Int,
        val startDate: String,
        val tasks: List<Task>,
        val title: String
    ) {
        data class Task(
            val endDate: EndDate,
            val isCompleted: Boolean,
            val startDate: StartDate,
            val taskId: Int,
            val title: String
        ) {
            data class EndDate(
                val day: Int,
                val hour: Int,
                val minute: Int,
                val month: Int,
                val year: Int
            )
    
            data class StartDate(
                val day: Int,
                val hour: Int,
                val minute: Int,
                val month: Int,
                val year: Int
            )
        }
    }
}