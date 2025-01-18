package com.example.gdg_c.data.model.repsonse.my


import com.example.gdg_c.data.model.calendar.CalendarDay

data class MyInfoResponse(
    val month: Int,
    val monthlyProgressResponses: List<CalendarDay>,
    val nickname: String,
    val processRatio: Int,
    val ranking: Int,
    val totalUsers: Int,
    val year: Int
)
