package com.example.gdg_c.data.model.calendar

import java.util.Calendar
import java.util.Date

data class CalendarData(
    val year: Int,
    val month: Int,
    val days: MutableList<CalendarDay>
) {
    // 연과 월을 가지고 시작 요일을 구하는 함수
    private fun getFirstDayOfMonth(): Int {
        // Calendar 객체 생성
        val calendar = Calendar.getInstance()

        // 연도와 월 설정 (월은 0부터 시작하므로 1을 빼야 합니다)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month - 1) // 월은 0부터 시작
        calendar.set(Calendar.DAY_OF_MONTH, 1) // 날짜를 1일로 설정

        // 요일 숫자 가져오기 (1=일요일, 7=토요일)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // 요일을 문자로 변환
        return when (dayOfWeek) {
            Calendar.SUNDAY -> 0
            Calendar.MONDAY -> 1
            Calendar.TUESDAY -> 2
            Calendar.WEDNESDAY -> 3
            Calendar.THURSDAY -> 4
            Calendar.FRIDAY -> 5
            Calendar.SATURDAY -> 6
            else -> 7
        }
    }

    // 시작 요일을 가지고 null 값을 days 에 넣어주는 함수
    fun addNullDays() {
        val addDays = getFirstDayOfMonth()

        for (i in 0 until addDays) {
            days.add(0, CalendarDay(null, null))
        }
    }

}