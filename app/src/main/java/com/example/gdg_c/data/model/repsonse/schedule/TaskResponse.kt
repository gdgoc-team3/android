package com.example.gdg_c.data.model.repsonse.schedule

import com.google.gson.annotations.SerializedName

data class TaskResponse(
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
        ) {
            override fun toString(): String {
                // "2025-01-01" 형식으로 반환
                return "%04d-%02d-%02d".format(year, month, day)
            }
        }

        data class StartDate(
            val day: Int,
            val hour: Int,
            val minute: Int,
            val month: Int,
            val year: Int
        ) {
            override fun toString(): String {
                // "2025-01-01" 형식으로 반환
                return "%04d-%02d-%02d".format(year, month, day)
            }
        }
    }
}