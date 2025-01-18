package com.example.gdg_c.ui.my

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gdg_c.data.local.PreferenceManager
import com.example.gdg_c.data.model.calendar.CalendarData
import com.example.gdg_c.data.model.calendar.CalendarDay
import com.example.gdg_c.data.model.repsonse.my.MyInfoResponse
import com.example.gdg_c.data.repository.MyRepository
import com.example.gdg_c.databinding.FragmentMyBinding
import com.example.gdg_c.ui.my.adapter.CalendarAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyFragment : Fragment() {

    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!

    private val calendarAdapter: CalendarAdapter = CalendarAdapter()
    private lateinit var calendarData: CalendarData
    private lateinit var calendarDayList: MutableList<CalendarDay>

    private val repository = MyRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMyInfo()

    }

    private fun getMyInfo() {

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val preferenceManager = PreferenceManager(requireContext())
            val userIdentity = preferenceManager.getUserIdentity()
            kotlin.runCatching {
                repository.getMyInfo(userIdentity!!, 2025, 1)
            }.onSuccess {
//                setUpCalendarAdapter(it.data)
                withContext(Dispatchers.Main) {
                    setUpCalendarAdapter(it.data)
                    setUpUserData(it.data)
                }
            }.onFailure {
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpUserData(data: MyInfoResponse) {
        binding.tvMyUserName.text = data.nickname
        binding.tvMyUserRank.text = "${data.ranking} / ${data.totalUsers}"

        binding.tvMyCalenderYear.text = "${data.year}년"
        binding.tvMyCalenderMonth.text = "${data.month}월"

        binding.pbMyUserRank.progress = data.processRatio
    }

    private fun setUpCalendarAdapter(data: MyInfoResponse) {
        calendarData = CalendarData(
            data.year, data.month, data.days.toMutableList()
        )
        calendarData.addNullDays()
        calendarDayList = calendarData.days
        calendarAdapter.submitList(calendarDayList)
        binding.rvMyCalender.apply {
            adapter = calendarAdapter
            layoutManager = GridLayoutManager(context, 7)
        }
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