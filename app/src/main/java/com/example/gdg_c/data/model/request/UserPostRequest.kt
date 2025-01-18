package com.example.gdg_c.data.model.request

data class UserPostRequest (
    val birthDate: String,
    val nickname: String,
    val userIdentity: String,
    val major: String,
    val desiredJob: String,
    val targetEmploymentPeriod: Int
)