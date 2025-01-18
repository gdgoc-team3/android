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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdg_c.R
import com.example.gdg_c.data.local.PreferenceManager
import com.example.gdg_c.data.model.calendar.CalendarData
import com.example.gdg_c.data.model.calendar.CalendarDay
import com.example.gdg_c.data.model.repsonse.schedule.TaskListResponse
import com.example.gdg_c.data.model.repsonse.schedule.TaskProgressResponse
import com.example.gdg_c.data.model.repsonse.schedule.TaskResponse
import com.example.gdg_c.data.repository.TaskRepository
import com.example.gdg_c.databinding.FragmentTaskBinding
import com.example.gdg_c.ui.task.adapter.TaskCalendarAdapter
import com.example.gdg_c.ui.task.adapter.TaskListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar

class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskCalendarAdapter: TaskCalendarAdapter
    private lateinit var calendarData: CalendarData
    private lateinit var calendarDayList: MutableList<CalendarDay>

    private val repository = TaskRepository()

    private val taskListAdapter = TaskListAdapter()

    private var startDate: Calendar? = Calendar.getInstance()
    private var endDate: Calendar? = Calendar.getInstance()


    private var taskList = mutableListOf<TaskListResponse.TaskListResponseItem.Task>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTaskBinding.inflate(inflater, container, false)

        setUpCalendar()
        initPostButton()


        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpCalendar() {
        val startMonth: Calendar = Calendar.getInstance().apply {
            set(2022, Calendar.JANUARY, 1)
        }
        val nowMonth: Calendar = Calendar.getInstance().apply {
            set(
                LocalDateTime.now().year,
                LocalDateTime.now().monthValue - 1,
                LocalDateTime.now().dayOfMonth
            )
        }
        val endMonth: Calendar = Calendar.getInstance().apply {
            set(
                2025, Calendar.DECEMBER, 31
            )
        }
        with(binding.cvTaskDateRangeCalendar) {
            setVisibleMonthRange(startMonth, endMonth)
            setCurrentMonth(nowMonth)
            setSelectableDateRange(startMonth, endMonth)
        }


    }

    // Dispatchers.io 안 써도 됨
    private fun initPostButton() {
        binding.btnPostTask.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {

                startDate = binding.cvTaskDateRangeCalendar.startDate
                endDate = binding.cvTaskDateRangeCalendar.endDate

                // SimpleDateFormat을 사용하여 형식 지정
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                val startDateFormat = dateFormat.format(startDate!!.time)
                val endDateFormat = dateFormat.format(endDate!!.time)

                val preferenceManager = PreferenceManager(requireContext())
                val userIdentity = preferenceManager.getUserIdentity()

                runCatching {

                    Log.d(
                        "parameter",
                        "startDate: $startDateFormat, endDate: $endDateFormat, mustDoTasks: ${binding.etTaskTask.text}, requirements: ${binding.etTaskAdditional.text}, title: ${binding.etTaskTitle.text}, userIdentity: dfjnsdfnj34"
                    )
                    repository.postTask(
                        endDate = endDateFormat,
                        mustDoTasks = binding.etTaskTask.text.toString(),
                        requirements = binding.etTaskAdditional.text.toString(),
                        startDate = startDateFormat,
                        title = binding.etTaskTitle.text.toString(),
                        userIdentity = userIdentity!!
                    )
                }.onSuccess {
                    // 성공 시
                    Log.d("success", "postTask: ${it.message}")
                    getTaskList()

                }.onFailure {
                    // 실패 시
                    Log.e("PANGMOO", "postTask: ${it.message}")
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTaskProgress()
        getTaskList()
    }

    private fun getTaskList() {
        viewLifecycleOwner.lifecycleScope.launch {

            val preferenceManager = PreferenceManager(requireContext())
            val userIdentity = preferenceManager.getUserIdentity()
            Log.d("PANGMOO", "getTaskList: $userIdentity")
            runCatching {
                repository.getTasks(
                    userIdentity = userIdentity!!,
                )
            }.onSuccess {
                // 성공 시
                withContext(Dispatchers.Main) {
                    setUpTaskList(it.data)
                }
            }.onFailure {
                // 실패 시
                Log.e("PANGMOO", "getTaskList: ${it.message}")
            }
        }
    }

    private fun setUpTaskList(data: TaskListResponse) {

        taskList = if (data.isEmpty()) mutableListOf() else data[0].tasks.toMutableList()

        binding.tvTaskListTitle.text = if (data.isEmpty()) "" else data[0].title

        binding.rvTaskList.adapter = taskListAdapter.apply {
            submitList(taskList)
        }
        binding.rvTaskList.layoutManager = LinearLayoutManager(context)
    }


    private fun getTaskProgress() {
        viewLifecycleOwner.lifecycleScope.launch {
            val preferenceManager = PreferenceManager(requireContext())
            val userIdentity = preferenceManager.getUserIdentity()
            runCatching {
                repository.getTaskProgress(
                    userIdentity = userIdentity!!
                )
            }.onSuccess {
                // 성공 시
                withContext(Dispatchers.Main) {
                    initTaskCalendarAdapter(it.data)
                }
            }.onFailure {
                // 실패 시
                Log.e("PANGMOO", "getTaskProgress: ${it.message}")
            }
        }
    }

    private fun initTaskCalendarAdapter(data: TaskProgressResponse) {

        taskCalendarAdapter = TaskCalendarAdapter { item ->
            Log.d("progress", "progress: $item.progress")
            if (item.progress != null) {
                if (item.progress > 0) {
                    binding.clAddTaskContainer.visibility = View.GONE
                    binding.clTaskListContainer.visibility = View.VISIBLE
                    binding.tvTaskListTitleDay.text = "${item.day}일 일정"
                    taskListAdapter.submitList(taskList)
                    taskListAdapter.updateList(
                        taskList,
                        item.day.toString())
                } else {
                    binding.clAddTaskContainer.visibility = View.VISIBLE
                    binding.clTaskListContainer.visibility = View.GONE
                    binding.tvTaskTitle.text = "${item.day}일 일정의 제목을 입력해주세요."
                }
            }
        }
        binding.rvTaskCalender.apply {
            adapter = taskCalendarAdapter
            layoutManager = GridLayoutManager(context, 7)
        }
        var list = data.toMutableList()
        list = addNullDays(
            list, 2025, 1
        )

        taskCalendarAdapter.submitList(list)
    }

    // 연과 월을 가지고 시작 요일을 구하는 함수
    private fun getFirstDayOfMonth(
        list: MutableList<TaskProgressResponse.TaskProgressResponseItem>,
        year: Int,
        month: Int
    ): Int {
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
    private fun addNullDays(
        list: MutableList<TaskProgressResponse.TaskProgressResponseItem>,
        year: Int,
        month: Int
    ): MutableList<TaskProgressResponse.TaskProgressResponseItem> {
        val addDays = getFirstDayOfMonth(
            list = list,
            year = year,
            month = month
        )

        for (i in 0 until addDays) {
            list.add(
                0, TaskProgressResponse.TaskProgressResponseItem(
                    null, null
                )
            )
        }
        return list
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}