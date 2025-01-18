package com.example.gdg_c.data.model.calendar

data class CalendarDay(
    val day: Int?,
    val progress: Int?,
    var hasTask: Boolean = false
) {
    init {
        hasTask = progress != 0
    }
}
