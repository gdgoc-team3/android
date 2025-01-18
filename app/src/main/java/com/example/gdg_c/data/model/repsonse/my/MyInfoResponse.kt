package com.example.gdg_c.data.model.repsonse.my


import com.example.gdg_c.data.model.calendar.CalendarDay
import com.google.gson.annotations.SerializedName

data class MyInfoResponse(
    val month: Int,
    @SerializedName("monthlyProgressResponses")
    val days: List<CalendarDay>,
    val nickname: String,
    val processRatio: Int,
    val ranking: Int,
    val totalUsers: Int,
    val year: Int
)
