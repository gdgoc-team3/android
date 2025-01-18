package com.example.gdg_c.ui.task

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gdg_c.R
import com.example.gdg_c.data.model.calendar.CalendarData
import com.example.gdg_c.data.model.calendar.CalendarDay
import com.example.gdg_c.data.repository.TaskRepository
import com.example.gdg_c.databinding.FragmentTaskBinding
import com.example.gdg_c.ui.task.adapter.TaskCalendarAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Calendar

class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskCalendarAdapter: TaskCalendarAdapter
    private lateinit var calendarData: CalendarData
    private lateinit var calendarDayList: MutableList<CalendarDay>

    private val repository = TaskRepository()

    private var startDate: Calendar? = Calendar.getInstance()
    private var endDate: Calendar? = Calendar.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskBinding.inflate(inflater, container, false)

        initTaskCalendarAdapter()
        setUpCalendar()
        initPostButton()


        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpCalendar() {
        val startMonth: Calendar = Calendar.getInstance().apply {
            set(2022, Calendar.JANUARY, 1)
        }
        val endMonth: Calendar = Calendar.getInstance().apply {
            set(
                LocalDateTime.now().year,
                LocalDateTime.now().monthValue - 1,
                LocalDateTime.now().dayOfMonth
            )
        }

        with(binding.cvTaskDateRangeCalendar) {
            setVisibleMonthRange(startMonth, endMonth)
            setCurrentMonth(endMonth)
            setSelectableDateRange(startMonth, endMonth)
        }

        startDate = binding.cvTaskDateRangeCalendar.startDate
        endDate = binding.cvTaskDateRangeCalendar.endDate

    }

    private fun initPostButton() {
        binding.btnPostTask.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                runCatching {
                    repository.postTask(
                        endDate = endDate.toString(),
                        mustDoTasks = binding.etTaskTask.toString(),
                        requirements = binding.etTaskAdditional.toString(),
                        startDate = startDate.toString(),
                        title = binding.etTaskTitle.toString(),
                        userIdentity = "dfjnsdfnj34"
                    )
                }.onSuccess {
                    // 성공 시
                }.onFailure {
                    // 실패 시
                    Log.e("PANGMOO", "postTask: ${it.message}")
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTaskCalendarAdapter()
        initTaskListAdapter()
    }

    private fun initTaskListAdapter() {
//        TODO("Not yet implemented")
    }

    private fun initTaskCalendarAdapter() {
        taskCalendarAdapter = TaskCalendarAdapter { hasTask ->
            if (hasTask) {
                binding.clAddTaskContainer.visibility = View.GONE
                binding.clTaskListContainer.visibility = View.VISIBLE
            } else {
                binding.clAddTaskContainer.visibility = View.VISIBLE
                binding.clTaskListContainer.visibility = View.GONE
            }
        }
        binding.rvTaskCalender.apply {
            adapter = taskCalendarAdapter
            layoutManager = GridLayoutManager(context, 7)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}