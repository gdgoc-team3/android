package com.example.gdg_c.data.model.request


data class TaskPostRequest(
    val endDate: String,
    val mustDoTasks: String,
    val requirements: String,
    val startDate: String,
    val title: String,
    val userIdentity: String
)