package com.example.gdg_c.ui.my

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gdg_c.R
import com.example.gdg_c.data.calendar.CalendarData
import com.example.gdg_c.data.calendar.CalendarDay
import com.example.gdg_c.databinding.FragmentMyBinding
import com.example.gdg_c.ui.my.adapter.CalendarAdapter

class MyFragment : Fragment() {

    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!

    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var calendarData: CalendarData
    private lateinit var calendarDayList: MutableList<CalendarDay>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDummy()

        calendarAdapter = CalendarAdapter().apply {
            submitList(calendarDayList)
        }
        binding.rvMyCalender.adapter = calendarAdapter
        binding.rvMyCalender.layoutManager = GridLayoutManager(context, 7)

    }

    private fun initDummy() {
        calendarData = CalendarData(
            2025, 1,
            mutableListOf(
                CalendarDay(1, 0),
                CalendarDay(2, 10),
                CalendarDay(3, 20),
                CalendarDay(4, 30),
                CalendarDay(5, 40),
                CalendarDay(6, 50),
                CalendarDay(7, 60),
                CalendarDay(8, 70),
                CalendarDay(9, 80),
                CalendarDay(10, 90),
                CalendarDay(11, 100),
                CalendarDay(12, 0),
                CalendarDay(13, 10),
                CalendarDay(14, 20),
                CalendarDay(15, 30),
                CalendarDay(16, 40),
                CalendarDay(17, 50),
                CalendarDay(18, 60),
                CalendarDay(19, 70),
                CalendarDay(20, 80),
                CalendarDay(21, 90),
                CalendarDay(22, 100),
                CalendarDay(23, 0),
                CalendarDay(24, 10),
                CalendarDay(25, 20),
                CalendarDay(26, 30),
                CalendarDay(27, 40),
                CalendarDay(28, 50),
                CalendarDay(29, 60),
                CalendarDay(30, 70),
                CalendarDay(31, 80),
            )
        )
        calendarData.addNullDays()

        calendarDayList = calendarData.days
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}