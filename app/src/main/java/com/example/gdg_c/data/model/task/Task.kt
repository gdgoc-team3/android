package com.example.gdg_c.data.model.task


data class Task(
    val isCompleted: Boolean,
    val startDate: StartDate,
    val taskId: Int,
    val title: String
)